package com.dfirago.jsh.rpi.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by dmfi on 13/01/2017.
 */
@Configuration
@ComponentScan
@Import(MoquetteConfiguration.class)
public class ApplicationConfiguration {

}
