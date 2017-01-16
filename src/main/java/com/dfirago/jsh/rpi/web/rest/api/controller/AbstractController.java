package com.dfirago.jsh.rpi.web.rest.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dmfi on 16/01/2017.
 */
public abstract class AbstractController {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractController.class);

    private final DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    @ResponseBody
    @RequestMapping(value = "/ping", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> ping() {
        LOG.info("Ping request received");
        String date = dateFormat.format(new Date());
        return new ResponseEntity<>(date, HttpStatus.OK);
    }
}
