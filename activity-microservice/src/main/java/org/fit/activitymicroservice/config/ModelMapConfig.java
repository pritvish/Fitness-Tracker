package org.fit.activitymicroservice.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapConfig {

    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }
}
