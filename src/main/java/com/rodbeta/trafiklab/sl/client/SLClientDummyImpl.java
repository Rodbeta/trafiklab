package com.rodbeta.trafiklab.sl.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rodbeta.trafiklab.sl.model.JourneyPattern;
import com.rodbeta.trafiklab.sl.model.Line;
import com.rodbeta.trafiklab.sl.model.SLResponse;
import com.rodbeta.trafiklab.sl.model.Site;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service("slClientDummyImpl")
public class SLClientDummyImpl implements SLClient {

    private final ObjectMapper objectMapper;

    public List<Line> getSLLines() throws SLClientException {

//        throw new SLClientException("hi error here", 500);
        log.info("get lines");
        var testResponse = """
                {
                  "StatusCode": 0,
                  "Message": null,
                  "ExecutionTime": 416,
                  "ResponseData": {
                    "Version": "2023-01-17 00:07",
                    "Type": "Line",
                    "Result": [
                      {
                        "LineNumber": "1",
                        "LineDesignation": "1",
                        "DefaultTransportMode": "blåbuss",
                        "DefaultTransportModeCode": "BUS",
                        "LastModifiedUtcDateTime": "2007-08-24 00:00:00.000",
                        "ExistsFromDate": "2007-08-24 00:00:00.000"
                      },
                      {
                        "LineNumber": "2",
                        "LineDesignation": "2",
                        "DefaultTransportMode": "blåbuss",
                        "DefaultTransportModeCode": "BUS",
                        "LastModifiedUtcDateTime": "2007-08-24 00:00:00.000",
                        "ExistsFromDate": "2007-08-24 00:00:00.000"
                      }
                    ]
                  }
                }
                """;

        try {
            var slResponse = objectMapper.readValue(testResponse, SLResponse.class);
            var result = slResponse.getResponseData().path("Result");
            return objectMapper.readValue(result.traverse(), new TypeReference<List<Line>>() {
            });
        } catch (Exception e) {
            throw new SLClientException(e.getMessage(), 500);
        }
    }

    @Override
    public List<JourneyPattern> getSLJourneyPattern() throws SLClientException {
        log.info("get jour");

        var testResponse = """
                {
                  "StatusCode": 0,
                  "Message": null,
                  "ExecutionTime": 355,
                  "ResponseData": {
                    "Version": "2023-01-17 00:07",
                    "Type": "JourneyPatternPointOnLine",
                    "Result": [
                      {
                        "LineNumber": "1",
                        "DirectionCode": "1",
                        "JourneyPatternPointNumber": "10008",
                        "LastModifiedUtcDateTime": "2022-02-15 00:00:00.000",
                        "ExistsFromDate": "2022-02-15 00:00:00.000"
                      }
                    ]
                  }
                }
                """;

        try {
            var slResponse = objectMapper.readValue(testResponse, SLResponse.class);
            var result = slResponse.getResponseData().path("Result");
            return objectMapper.readValue(result.traverse(), new TypeReference<List<JourneyPattern>>() {
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Site> getSLSites() {
        log.info("get sites");
        var testResponse = """
                {
                  "StatusCode": 0,
                  "Message": null,
                  "ExecutionTime": 321,
                  "ResponseData": {
                    "Version": "2023-01-17 00:07",
                    "Type": "Site",
                    "Result": [
                      {
                        "SiteId": "4432",
                        "SiteName": "Abborrkroksvägen",
                        "StopAreaNumber": "10008",
                        "LastModifiedUtcDateTime": "2015-02-13 10:34:20.643",
                        "ExistsFromDate": "2015-02-14 00:00:00.000"
                      },
                                            {
                        "SiteId": "4431",
                        "SiteName": "second site",
                        "StopAreaNumber": "10008",
                        "LastModifiedUtcDateTime": "2015-02-13 10:34:20.643",
                        "ExistsFromDate": "2015-02-14 00:00:00.000"
                      }
                    ]
                  }
                }
                """;

        try {
            var slResponse = objectMapper.readValue(testResponse, SLResponse.class);
            var result = slResponse.getResponseData().path("Result");
            return objectMapper.readValue(result.traverse(), new TypeReference<List<Site>>() {
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
