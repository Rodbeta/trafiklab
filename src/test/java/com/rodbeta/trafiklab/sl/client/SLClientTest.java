package com.rodbeta.trafiklab.sl.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SLClientTest {
   SLClient slClient;

    @BeforeAll
    public void setUp() {
        slClient = new SLClientDummyImpl(new ObjectMapper());
    }
    @Test
    public void getSLLines() throws SLClientException {
        var lineList = slClient.getSLLines();
        assertFalse(lineList.isEmpty());
        assertEquals(2, lineList.size());
    }

    @Test
    public void getSLSites() throws SLClientException {
        var siteList = slClient.getSLSites();
        assertFalse(siteList.isEmpty());
        assertEquals(2, siteList.size());    }

    @Test
    public void getSLJourneyPattern() throws SLClientException {
        var jourList = slClient.getSLJourneyPattern();
        assertFalse(jourList.isEmpty());
        assertEquals(1, jourList.size());    }
}
