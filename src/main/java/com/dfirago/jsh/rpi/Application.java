package com.dfirago.jsh.rpi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

/**
 * Created by dmfi on 12/01/2017.
 */
@SpringBootApplication
public class Application {
    
    public static void main(String[] args) throws IOException {
        SpringApplication.run(Application.class, args);
    }
}
