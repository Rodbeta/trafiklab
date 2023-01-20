package com.rodbeta.trafiklab.resources;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SLServiceTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void getTop10() {
        var response = this.restTemplate.getForEntity("/lines", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        var lines = response.getBody();
        assertNotNull(lines);
    }

    @Test
    void getTop10_error() {
        var response = this.restTemplate.getForEntity("/lines", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        var lines = response.getBody();
        assertNotNull(lines);
    }
}