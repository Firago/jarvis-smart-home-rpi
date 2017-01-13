package com.dfirago.jsh.rpi.config;

import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Created by dmfi on 13/01/2017.
 */
@Configuration
public class WebConfiguration {

    @Bean
    public ServletRegistrationBean h2servletRegistration() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
        servletRegistrationBean.setServlet(new WebServlet());
        servletRegistrationBean.addUrlMappings("/console/*");
        return servletRegistrationBean;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}