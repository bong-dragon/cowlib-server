package com.cowlib.client;

import com.cowlib.model.BookMetaSearch;
import com.cowlib.model.BookMetaSearchResult;
import com.cowlib.util.PropertyFileLoader;
import org.junit.Before;
import org.junit.Test;

import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;

public class DaumBookClientTest {

    private String apikey;

    @Before
    public void setUp() {
        Properties properties = PropertyFileLoader.getProperties("application-local.properties");
        apikey = properties.getProperty("daum.api.book");
    }

    @Test
    public void search() throws Exception {
        // Given
        DaumBookClient client = new DaumBookClient(apikey);
        BookMetaSearch bookMetaSearch = new BookMetaSearch();
        bookMetaSearch.setQ("Hello");
        bookMetaSearch.setPageno(1);

        // When
        BookMetaSearchResult searchResult = client.search(bookMetaSearch);

        // Then
        assertThat(searchResult.getBookMetas().size()).isEqualTo(10);
    }
}