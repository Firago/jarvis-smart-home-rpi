package com.dfirago.jsh.rpi.config;

import com.dfirago.jsh.rpi.web.mqtt.interceptor.PublishedMessageHandler;
import io.moquette.interception.InterceptHandler;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.util.Arrays;
import java.util.List;

/**
 * Created by dmfi on 13/01/2017.
 */
@Configuration
public class MoquetteConfiguration {

    @Bean(name = "moquetteProperties")
    public PropertiesFactoryBean moquetteProperties() {
        PropertiesFactoryBean bean = new PropertiesFactoryBean();
        bean.setLocation(new ClassPathResource("config/moquette-server.properties"));
        return bean;
    }

    @Bean(name = "moquetteInterceptors")
    public List<InterceptHandler> moquetteInterceptors() {
        return Arrays.asList(
                publishedMessageHandler()
        );
    }

    @Bean(name = "publishedMessageHandler")
    public PublishedMessageHandler publishedMessageHandler() {
        return new PublishedMessageHandler();
    }
}
