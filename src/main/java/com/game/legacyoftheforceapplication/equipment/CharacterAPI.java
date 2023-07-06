package com.game.legacyoftheforceapplication.equipment;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class CharacterAPI {

    public void getCharacter(Long characterId) {

        HttpClient httpClient = HttpClient.newHttpClient();

        String url = String.format("http://localhost:8080/api/%d", characterId);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            // Process the response from Service B
            int statusCode = response.statusCode();
            String responseBody = response.body();

            System.out.println("Response status code: " + statusCode);
            System.out.println("Response body: " + responseBody);
        } catch(IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
