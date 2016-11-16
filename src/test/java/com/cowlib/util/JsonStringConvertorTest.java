package com.cowlib.util;

import com.cowlib.exception.InvalidJsonFormatException;
import org.junit.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonStringConvertorTest {

    @Test
    public void convertJsonStringToMap_success() throws Exception {
        // Given
        String mailMeta = PropertyFileLoader.getResourceAsString("json/book/book.json");

        // When
        Map mailMetaMap = JsonStringConvertor.convertJsonStringToMap(mailMeta);

        // Then
        assertThat(mailMetaMap).isNotEmpty();
    }

    @Test(expected = InvalidJsonFormatException.class)
    public void convertJsonStringToMap_parseException() throws Exception {
        // Given
        String mailMeta = PropertyFileLoader.getResourceAsString("json/book/book.json.invalid");

        // When
        Map mailMetaMap = JsonStringConvertor.convertJsonStringToMap(mailMeta);
    }

}