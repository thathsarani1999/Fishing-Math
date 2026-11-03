package com.perisic.fish.trivia;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

public class TriviaAPI {

    private static final String API_URL =
        "https://the-trivia-api.com/v2/questions?limit=1";

    public static TriviaQuestion getRandomQuestion() throws Exception {

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .GET()
                .build();

        HttpResponse<String> response = client.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );

        if (response.statusCode() != 200) {
            throw new Exception("Trivia API error: " + response.statusCode());
        }

        JsonArray questions = JsonParser.parseString(
                response.body()
        ).getAsJsonArray();

        if (questions.isEmpty()) {
            throw new Exception("No trivia questions available.");
        }

        Gson gson = new Gson();

        return gson.fromJson(
                questions.get(0),
                TriviaQuestion.class
        );
    }
}