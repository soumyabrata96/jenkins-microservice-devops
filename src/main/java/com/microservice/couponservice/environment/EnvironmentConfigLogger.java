package com.microservice.couponservice.environment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.StreamSupport;

@Component
public class EnvironmentConfigLogger {

    private final Logger LOGGER= LoggerFactory.getLogger(EnvironmentConfigLogger.class);
    @SuppressWarnings("rawtypes")
    @EventListener
    public void handleContextRefresh(ContextRefreshedEvent contextRefreshedEvent){
        final Environment environment = contextRefreshedEvent.getApplicationContext().getEnvironment();
        LOGGER.info("====== Environment and configuration ======");
        LOGGER.info("Active profiles: {}", Arrays.toString(environment.getActiveProfiles()));
        final MutablePropertySources sources = ((AbstractEnvironment) environment).getPropertySources();
        StreamSupport.stream(sources.spliterator(), false).filter(ps -> ps instanceof EnumerablePropertySource)
                .map(ps -> ((EnumerablePropertySource) ps).getPropertyNames()).flatMap(Arrays::stream).distinct()
                .forEach(prop -> {
                    Object resolved = environment.getProperty(prop, Object.class);
                    if (resolved instanceof String) {
                        LOGGER.info("{} - {}", prop, environment.getProperty(prop));
                    } else {
                        LOGGER.info("{} - {}", prop, "NON-STRING-VALUE");
                    }

                });
        LOGGER.debug("===========================================");
    }
}
