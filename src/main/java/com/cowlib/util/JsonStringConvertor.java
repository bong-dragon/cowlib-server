package com.cowlib.util;

import com.cowlib.exception.InvalidJsonFormatException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

public class JsonStringConvertor {
    public static Map<String, Object> convertJsonStringToMap(String jsonString) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(jsonString, new TypeReference<Map<String, Object>>() {
            });
        } catch (IOException e) {
            throw new InvalidJsonFormatException(jsonString, e);
        }
    }
}
