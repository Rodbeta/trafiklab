package com.rodbeta.trafiklab.sl.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rodbeta.trafiklab.sl.model.JourneyPattern;
import com.rodbeta.trafiklab.sl.model.Line;
import com.rodbeta.trafiklab.sl.model.SLResponse;
import com.rodbeta.trafiklab.sl.model.Site;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service("slClientImpl")
public class SLClientImpl implements SLClient {

    private static final Client client = ClientBuilder.newClient();
    private final ObjectMapper objectMapper;
    private static final String LOCATION = "https://api.sl.se/api2";
    final static String KEY = "272f7993eef841a1a3a95a4c4594ff84";

    public List<Line> getSLLines() throws SLClientException {
        try {
            var response = client
                    .target(LOCATION)
                    .path("LineData.json")
                    .queryParam("key", KEY)
                    .queryParam("model", "line")
                    .queryParam("DefaultTransportModeCode", "BUS")
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get(SLResponse.class);
            if (0 != response.getStatusCode()) {
                throw new SLClientException("%s".formatted(response.getMessage()), response.getStatusCode());
            }
            var result = response.getResponseData().path("Result");
            return objectMapper.readValue(result.traverse(), new TypeReference<List<Line>>() {
            });
        } catch (Exception e) {
            throw new SLClientException("%s".formatted(e.getMessage()), 500);
        }
    }

    @Override
    public List<JourneyPattern> getSLJourneyPattern() throws SLClientException {
        try {
            var response = client
                    .target(LOCATION)
                    .path("LineData.json")
                    .queryParam("key", KEY)
                    .queryParam("model", "jour")
                    .queryParam("DefaultTransportModeCode", "BUS")
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get(SLResponse.class);
            if (0 != response.getStatusCode()) {
                throw new SLClientException("%s".formatted(response.getMessage()), response.getStatusCode());
            }
            var result = response.getResponseData().path("Result");
            return objectMapper.readValue(result.traverse(), new TypeReference<List<JourneyPattern>>() {});
        } catch (Exception e) {
            throw new SLClientException("%s".formatted(e.getMessage()), 500);
        }
    }

    @Override
    public List<Site> getSLSites() throws SLClientException {
        try {
            var response = client
                    .target(LOCATION)
                    .path("LineData.json")
                    .queryParam("key", KEY)
                    .queryParam("model", "site")
                    .queryParam("DefaultTransportModeCode", "BUS")
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get(SLResponse.class);
            if (0 != response.getStatusCode()) {
                throw new SLClientException("%s".formatted(response.getMessage()), response.getStatusCode());
            }
            var result = response.getResponseData().path("Result");
            return objectMapper.readValue(result.traverse(), new TypeReference<List<Site>>() {});
        } catch (Exception e) {
            throw new SLClientException("%s".formatted(e.getMessage()), 500);
        }
    }
}
