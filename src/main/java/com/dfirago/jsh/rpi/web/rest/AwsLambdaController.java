package com.dfirago.jsh.rpi.web.rest;

import com.dfirago.jsh.rpi.web.mqtt.MoquetteServer;
import com.dfirago.jsh.rpi.web.rest.model.AwsLambdaRequest;
import com.dfirago.jsh.rpi.web.rest.model.AwsLambdaResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dmfi on 13/01/2017.
 */
@RestController
public class AwsLambdaController {

    private static final Logger LOG = LoggerFactory.getLogger(AwsLambdaController.class);

    private final static DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy hh:mm:ss");

    @Autowired
    private MoquetteServer moquetteServer;

    @ResponseBody
    @RequestMapping(value = "/ping", produces = "text/plain")
    public ResponseEntity<String> ping() {
        LOG.debug("Ping request received");
        String date = dateFormat.format(new Date());
        return new ResponseEntity<>(date, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/action", produces = "application/json", method = RequestMethod.POST)
    public AwsLambdaResponse handleAwsLambdaRequest(@RequestBody AwsLambdaRequest request) throws JsonProcessingException {
        LOG.debug("AwsLambdaResponse request received: {}", request);
        moquetteServer.publish("TODO", request);
        return new AwsLambdaResponse();
    }
}
