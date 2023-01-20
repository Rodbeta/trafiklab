package com.rodbeta.trafiklab.sl;


import com.rodbeta.trafiklab.sl.client.SLClient;
import com.rodbeta.trafiklab.sl.client.SLClientException;
import com.rodbeta.trafiklab.sl.model.JourneyPattern;
import com.rodbeta.trafiklab.sl.model.Line;
import com.rodbeta.trafiklab.sl.model.Site;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SLManager {

    @Autowired
    @Qualifier("slClientImpl")
    // @Qualifier("slClientDummyImpl")
    SLClient slClient;

    public Map<Integer, List<String>> getSLTop10Lines() throws SLClientException {
        var lines = slClient.getSLLines();
        var sites = slClient.getSLSites();
        var journeyPatterns = slClient.getSLJourneyPattern();

        return lines.stream()
                .distinct()
                .limit(10)
                .collect(Collectors.toMap(Line::getLineNumber, line -> {
                            var journeyPatternsOfLine = journeyPatterns.stream()
                                    .filter(journeyPattern -> journeyPattern.getLineNumber() == line.getLineNumber())
                                    .map(JourneyPattern::getJourneyPatternPointNumber)
                                    .toList();
                            return sites.stream()
                                    .filter(site -> journeyPatternsOfLine.contains(site.getStopAreaNumber()))
                                    .map(Site::getSiteName)
                                    .distinct()
                                    .toList();
                        })
                );
    }
}
