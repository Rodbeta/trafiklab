package com.rodbeta.trafiklab.sl.client;

import com.rodbeta.trafiklab.sl.model.JourneyPattern;
import com.rodbeta.trafiklab.sl.model.Line;
import com.rodbeta.trafiklab.sl.model.Site;

import java.util.List;

public interface SLClient {
    List<Line> getSLLines() throws SLClientException;
    List<JourneyPattern> getSLJourneyPattern() throws SLClientException;

    List<Site> getSLSites() throws SLClientException;

}
