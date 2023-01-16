package at.fhtw.swen3.service.impl;

import at.fhtw.swen3.configuration.service.GeoEncodingService;
import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class OpenStreetMapsProxy implements GeoEncodingService {

    @Override
    public GeoCoordinateEntity encodeAddress(String address) throws IOException, InterruptedException {
        String baseUrl = "https://nominatim.openstreetmap.org/search?";
        StringBuilder uriBuilder = new StringBuilder();
        uriBuilder.append(baseUrl);
        uriBuilder.append("q=").append(URLEncoder.encode(address, StandardCharsets.UTF_8));
        uriBuilder.append("&format=json");

        URI uri = URI.create(uriBuilder.toString());

        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();

        HttpClient client = HttpClient.newHttpClient();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        GeoCoordinateEntity[] geolocation = objectMapper.readValue(response.body(), GeoCoordinateEntity[].class);
        System.out.println(geolocation[0].toString());
        return geolocation[0];
    }

}
