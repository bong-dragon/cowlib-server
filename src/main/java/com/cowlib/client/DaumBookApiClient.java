package com.cowlib.client;

import com.cowlib.model.Book;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by KAKAO on 2016. 10. 30..
 */
public class DaumBookApiClient {
    public List<Book> search(String q) {
        RestTemplate restTemplate = new RestTemplate();
        String uri = "https://apis.daum.net/search/book?output=json&apikey=0e07fcf5690bd28508f96c9415ecdeeb&q=" + q;
        String result = restTemplate.getForObject(uri, String.class);
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
            System.out.println(jsonBook);
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
