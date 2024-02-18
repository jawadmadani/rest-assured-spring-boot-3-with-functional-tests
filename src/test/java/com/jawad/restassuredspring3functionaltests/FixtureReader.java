package com.jawad.restassuredspring3functionaltests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import wiremock.org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public class FixtureReader {

    public static <T> T get(String filename, final Class<T> cls) {
        String fileContent = readFileContent(filename);
        return convertResponseToObject(fileContent, cls);
    }

    protected static String readFileContent(String filename) {
        InputStream resource;
        String fixtureContent;
        try {
            resource = new ClassPathResource(String.format("/fixtures/%s", filename)).getInputStream();
            fixtureContent = IOUtils.toString(resource, Charset.defaultCharset());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fixtureContent;
    }

    private static <T> T convertResponseToObject(final String fileContent, final Class<T> cls) {
        try {
            return (new ObjectMapper()).readValue(fileContent, cls);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
