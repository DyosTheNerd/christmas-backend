package jp.co.axa.apidemo.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * this class handles the main application configuration and provides configured ootb beans
 */
@Configuration
public class ApplicationConfiguration {


    /**
     * Defining the Model Mapper, in this context the default implementation is sufficient
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
