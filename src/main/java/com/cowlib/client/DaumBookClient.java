package com.cowlib.client;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.cowlib.exception.CowlibRuntimeException;
import com.cowlib.model.BookMeta;
import com.cowlib.model.BookMetaSearch;
import com.cowlib.model.BookMetaSearchResult;
import com.cowlib.util.JsonStringConvertor;

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

    public BookMetaSearchResult search(BookMetaSearch search) {
        String url = BuildUrl(search);
        String jsonString = restTemplate.getForObject(url, String.class);
        return parse(jsonString);
    }

    private String BuildUrl(BookMetaSearch search) {
        String decodedUrl = buildEncodedUrl(search);
        try {
            decodedUrl = URLDecoder.decode(decodedUrl, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new CowlibRuntimeException("url decode fail", e);
        }
        return decodedUrl;
    }

    private String buildEncodedUrl(BookMetaSearch search) {
        UriComponentsBuilder builder = UriComponentsBuilder.newInstance();
        return builder.scheme(HTTPS)
                .host(APIS_DAUM_NET)
                .path(SEARCH_BOOK)
                .queryParam("apikey", apikey)
                .queryParam("output", "json")
                .queryParam("q", search.getQ())
                .queryParam("pageno", search.getPageno())
                .toUriString();
    }

    private BookMetaSearchResult parse(String jsonString) {
        Map<String, Object> jsonMap = JsonStringConvertor.convertJsonStringToMap(jsonString);
        Map<String, Object> channel = (Map<String, Object>) jsonMap.get("channel");

        int totalCount = Integer.parseInt((String) channel.get("totalCount"));
        List<Map<String, Object>> booksFromDaum = (java.util.List<Map<String, Object>>) channel.get("item");
        List<BookMeta> bookMetas = convertToBook(booksFromDaum);

        return new BookMetaSearchResult(bookMetas, totalCount);
    }

    private List<BookMeta> convertToBook(List<Map<String, Object>> booksFromDaum) {
        List<BookMeta> bookMetas = new ArrayList<>();
        for (Map<String, Object> daumBook : booksFromDaum) {
            String isbn = (String) daumBook.get("isbn");
            String isbn13 = (String) daumBook.get("isbn13");
            String title = (String) daumBook.get("title");
            String author = (String) daumBook.get("author_t");
            String description = (String) daumBook.get("description");
            String publisher = (String) daumBook.get("pub_nm");
            String coverUrl = (String) daumBook.get("cover_l_url");

            BookMeta bookMeta = new BookMeta();
            bookMeta.setIsbn(isbn);
            bookMeta.setIsbn13(isbn13);
            bookMeta.setTitle(filter(title));
            bookMeta.setAuthor(filter(author));
            bookMeta.setDescription(filter(description));
            bookMeta.setPublisher(filter(publisher));
            bookMeta.setCoverUrl(coverUrl);
            bookMetas.add(bookMeta);
        }
        return bookMetas;
    }

    private String filter(String word) {
        return word.replaceAll("&lt;b&gt;", "").replaceAll("&lt;/b&gt;", "");
    }

}
