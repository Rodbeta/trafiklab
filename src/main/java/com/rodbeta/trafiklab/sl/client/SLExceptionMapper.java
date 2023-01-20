package com.rodbeta.trafiklab.sl.client;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

public class SLExceptionMapper implements ExceptionMapper<SLClientException>{

    public Response toResponse(SLClientException exception) {
        String message = exception.getMessage();
        Response response = exception.getResponse();
        Response.Status status = response.getStatusInfo().toEnum();

        return Response.status(status)
                .entity(status + ": " + message)
                .type(MediaType.TEXT_PLAIN)
                .build();
    }
}
