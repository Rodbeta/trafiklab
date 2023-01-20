package com.rodbeta.trafiklab.sl;

import com.rodbeta.trafiklab.sl.client.SLClient;
import com.rodbeta.trafiklab.sl.client.SLClientException;
import com.rodbeta.trafiklab.sl.model.JourneyPattern;
import com.rodbeta.trafiklab.sl.model.Line;
import com.rodbeta.trafiklab.sl.model.Site;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SLManagerTest {

    @Mock
    SLClient slClient;

    @InjectMocks
    SLManager slManager;

    @Test
    public void getSLTop10Lines_success() throws SLClientException {
        when(slClient.getSLLines()).thenReturn(List.of(
                Line.builder().lineNumber(1).DefaultTransportModeCode("BUS").build(),
                Line.builder().lineNumber(2).DefaultTransportModeCode("BUS").build(),
                Line.builder().lineNumber(3).DefaultTransportModeCode("BUS").build(),
                Line.builder().lineNumber(4).DefaultTransportModeCode("BUS").build(),
                Line.builder().lineNumber(5).DefaultTransportModeCode("BUS").build(),
                Line.builder().lineNumber(6).DefaultTransportModeCode("BUS").build(),
                Line.builder().lineNumber(7).DefaultTransportModeCode("BUS").build(),
                Line.builder().lineNumber(8).DefaultTransportModeCode("BUS").build(),
                Line.builder().lineNumber(9).DefaultTransportModeCode("BUS").build(),
                Line.builder().lineNumber(10).DefaultTransportModeCode("BUS").build(),
                Line.builder().lineNumber(11).DefaultTransportModeCode("BUS").build()
        ));

        when(slClient.getSLJourneyPattern()).thenReturn(List.of(
                JourneyPattern.builder().lineNumber(1).journeyPatternPointNumber(1234).build(),
                JourneyPattern.builder().lineNumber(2).journeyPatternPointNumber(1244).build(),
                JourneyPattern.builder().lineNumber(3).journeyPatternPointNumber(1788).build()
        ));

        when(slClient.getSLSites()).thenReturn(List.of(
                Site.builder().siteName("Adler Salvius väg").stopAreaNumber(1234).build(),
                Site.builder().siteName("Second stop").stopAreaNumber(1244).build(),
                Site.builder().siteName("Third stop").stopAreaNumber(1788).build(),
                Site.builder().siteName("Fourth stop").stopAreaNumber(1788).build()
        ));

        var top10Lines = slManager.getSLTop10Lines();

        assertFalse(top10Lines.isEmpty());
        assertEquals(10, top10Lines.size());
        var firstLine = top10Lines.get(1);
        assertEquals(1, firstLine.size());
        assertEquals("Adler Salvius väg", firstLine.get(0));
        assertEquals(2, top10Lines.get(3).size());
    }

    @Test
    public void getSLTop10Lines_failure_to_fetch() throws SLClientException {
        when(slClient.getSLLines()).thenThrow(new SLClientException("Unable to fetch lines from SL", 500));

        Exception exception = assertThrows(SLClientException.class, () -> {
            slManager.getSLTop10Lines();
        });
        assertTrue(exception.getMessage().contains("Unable to fetch lines from SL"));
    }
}