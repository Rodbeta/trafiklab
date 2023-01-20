package com.rodbeta.trafiklab.config;

import com.rodbeta.trafiklab.resources.SLService;
import com.rodbeta.trafiklab.sl.client.SLExceptionMapper;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(SLService.class);
        register(SLExceptionMapper.class);
        property(ServletProperties.FILTER_FORWARD_ON_404, true);
    }
}