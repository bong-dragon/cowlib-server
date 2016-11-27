package com.cowlib.client;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import com.cowlib.model.BookMeta;
import com.cowlib.model.BookMetaSearch;
import com.cowlib.util.PropertyFileLoader;

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
        List<BookMeta> bookMetas = client.search(bookMetaSearch);

        // Then
        assertThat(bookMetas.size()).isEqualTo(10);
    }

}