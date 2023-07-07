package com.game.legacyoftheforceapplication.utility;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.stream.Collectors;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class LegacyOfTheForceAPIConnection {

    private static final Logger logger = LogManager.getLogger(LegacyOfTheForceAPIConnection.class);
    private static final String URL = "http://localhost:8080/api";

    public String get(Long id) {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL + String.format("/%d", id)))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        return handleResponse(request, httpClient);
    }

    public void post(Map<String, Object> body) {
        HttpClient httpClient = HttpClient.newHttpClient();
        String requestBodyJson = toJson(body);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBodyJson))
                .build();

        handleResponse(request, httpClient);
    }

    public void put(Long id, Map<String, Object> body) {
        HttpClient httpClient = HttpClient.newHttpClient();
        String requestBodyJson = toJson(body);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL + String.format("/%d", id)))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(requestBodyJson))
                .build();

        handleResponse(request, httpClient);
    }

    public void delete(Long id) {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL + String.format("/%d", id)))
                .header("Content-Type", "application/json")
                .DELETE()
                .build();

        handleResponse(request, httpClient);
    }

    private String toJson(Map<String, Object> data) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }

    private String handleResponse(HttpRequest request, HttpClient httpClient) {
        String responseBody = null;
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            int statusCode = response.statusCode();
            responseBody = response.body();


        } catch(IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return responseBody;
    }

    private URI buildUriWithQueryParams(Map<String, String> queryParams) {
        String queryString = queryParams.entrySet().stream()
                .map(entry -> URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8) + "=" +
                        URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8))
                .collect(Collectors.joining("&"));

        String uriString = URL + (queryString.isEmpty() ? "" : "?" + queryString);

        return URI.create(uriString);
    }
}
