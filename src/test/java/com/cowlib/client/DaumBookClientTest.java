package com.cowlib.client;

import com.cowlib.model.Book;
import com.cowlib.util.PropertyFileLoader;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
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

        // When
        List<Book> books = client.search("Hello");

        // Then
        assertThat(books.size()).isEqualTo(10);
    }

}