package com.skyforce.SkyForceWebService.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class JSONConvert {
    private static ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

    public static String JSONConverter (Object obj) {
        String json = obj.toString();
        try {
            json = ow.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

}
