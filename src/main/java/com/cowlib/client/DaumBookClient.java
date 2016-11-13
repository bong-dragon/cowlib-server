package com.cowlib.client;

import com.cowlib.model.Book;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DaumBookClient {
    private static final String APIS_DAUM_NET = "apis.daum.net";
    private static final String HTTPS = "https";
    private static final String SEARCH_BOOK = "/search/book";

    @Value("${daum.api.book}")
    private String apikey;

    private RestTemplate restTemplate = new RestTemplate();

    public DaumBookClient() {
    }

    public DaumBookClient(String apikey) {
        this.apikey = apikey;
    }

    public List<Book> search(String q) {
        String uri = buildUri(q);
        String jsonString = restTemplate.getForObject(uri, String.class);

        return parse(jsonString);
    }

    private String buildUri(String q) {
        UriComponentsBuilder builder = UriComponentsBuilder.newInstance();
        return builder.scheme(HTTPS)
                .host(APIS_DAUM_NET)
                .path(SEARCH_BOOK)
                .queryParam("apikey", apikey)
                .queryParam("output", "json")
                .queryParam("q", q)
                .toUriString();
    }

    private List<Book> parse(String result) {
        Map<String, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            //convert JSON string to Map
            map = mapper.readValue(result, new TypeReference<HashMap<String, Object>>() {
            });
        } catch (Exception e) {
            System.out.println("Exception converting {} to map" + result);
        }

        Map<String, Object> channel = (Map<String, Object>) map.get("channel");
        List<Map<String, Object>> List = (java.util.List<Map<String, Object>>) channel.get("item");

        List<Book> books = new ArrayList<>();
        for (Map<String, Object> jsonBook : List) {
            String isbn = (String) jsonBook.get("isbn");
            String isbn13 = (String) jsonBook.get("isbn13");
            String title = (String) jsonBook.get("title");
            String author = (String) jsonBook.get("author_t");
            String description = (String) jsonBook.get("description");
            String publisher = (String) jsonBook.get("pub_nm");
            String coverUrl = (String) jsonBook.get("cover_l_url");

            Book book = new Book()
                    .setIsbn(isbn)
                    .setIsbn13(isbn13)
                    .setTitle(title)
                    .setAuthor(author)
                    .setDescription(description)
                    .setPublisher(publisher)
                    .setCoverUrl(coverUrl);
            books.add(book);
        }
        return books;
    }
}
