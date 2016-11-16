package com.cowlib.client;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cowlib.exception.CowlibRuntimeException;
import com.cowlib.util.JsonStringConvertor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.cowlib.model.Book;

@Component
public class DaumBookClient {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
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
        String url = BuildUrl(q);
        String jsonString = restTemplate.getForObject(url, String.class);
        return parse(jsonString);
    }

    private String BuildUrl(String q) {
        String decodedUrl = buildEncodedUrl(q);
        try {
            decodedUrl = URLDecoder.decode(decodedUrl, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new CowlibRuntimeException("url decode fail", e);
        }
        return decodedUrl;
    }

    private String buildEncodedUrl(String q) {
        UriComponentsBuilder builder = UriComponentsBuilder.newInstance();
        return builder.scheme(HTTPS)
                .host(APIS_DAUM_NET)
                .path(SEARCH_BOOK)
                .queryParam("apikey", apikey)
                .queryParam("output", "json")
                .queryParam("q", q)
                .toUriString();
    }

    private List<Book> parse(String jsonString) {
        Map<String, Object> jsonMap = JsonStringConvertor.convertJsonStringToMap(jsonString);
        Map<String, Object> channel = (Map<String, Object>) jsonMap.get("channel");
        List<Map<String, Object>> booksFromDaum = (java.util.List<Map<String, Object>>) channel.get("item");

        return convertToBook(booksFromDaum);
    }

    private List<Book> convertToBook(List<Map<String, Object>> booksFromDaum) {
        List<Book> books = new ArrayList<>();
        for (Map<String, Object> daumBook : booksFromDaum) {
            String isbn = (String) daumBook.get("isbn");
            String isbn13 = (String) daumBook.get("isbn13");
            String title = (String) daumBook.get("title");
            String author = (String) daumBook.get("author_t");
            String description = (String) daumBook.get("description");
            String publisher = (String) daumBook.get("pub_nm");
            String coverUrl = (String) daumBook.get("cover_l_url");

            Book book = new Book();
            book.setIsbn(isbn);
            book.setIsbn13(isbn13);
            book.setTitle(title);
            book.setAuthor(author);
            book.setDescription(description);
            book.setPublisher(publisher);
            book.setCoverUrl(coverUrl);
            books.add(book);
        }
        return books;
    }

}
