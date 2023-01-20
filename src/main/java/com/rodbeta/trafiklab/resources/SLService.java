package com.rodbeta.trafiklab.resources;

import com.rodbeta.trafiklab.sl.SLManager;
import com.rodbeta.trafiklab.sl.client.SLClientException;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
@Path("/lines")
public class SLService {
    @Autowired
    SLManager slManager;

    @GET
    @Produces({"application/json"})
    public Map<Integer, List<String>> getTop10() throws SLClientException {
        log.info("inside top 10");
        return slManager.getSLTop10Lines();
    }
}
