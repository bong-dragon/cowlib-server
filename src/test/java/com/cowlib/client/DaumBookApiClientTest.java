package com.cowlib.client;

import com.cowlib.model.Book;

import org.junit.Test;

import java.util.Map;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DaumBookApiClientTest {

    @Test
    public void get() throws Exception {
        DaumBookApiClient client = new DaumBookApiClient();
        List<Book> books = client.search("1234");

        assertThat(books.size()).isEqualTo(10);
    }


}