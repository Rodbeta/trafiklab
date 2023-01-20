package com.rodbeta.trafiklab.sl.client;

import jakarta.ws.rs.WebApplicationException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SLClientException extends WebApplicationException {
    public SLClientException (String message, int status) {
        super(message, status);
        log.warn(message);
    }
}
