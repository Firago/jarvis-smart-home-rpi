package com.dfirago.jsh.rpi.web.rest.api.controller;

import com.dfirago.jsh.rpi.domain.DeviceInfo;
import com.dfirago.jsh.rpi.service.ActionLogService;
import com.dfirago.jsh.rpi.service.DeviceInfoService;
import com.dfirago.jsh.rpi.web.mqtt.MoquetteServer;
import com.dfirago.jsh.rpi.web.mqtt.model.DeviceStateChangeRequest;
import com.dfirago.jsh.rpi.web.rest.api.model.ActionRequest;
import com.dfirago.jsh.rpi.web.rest.api.model.ActionResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dmfi on 13/01/2017.
 */
@RestController
@RequestMapping("/aws")
public class AwsLambdaController {

    private static final Logger LOG = LoggerFactory.getLogger(AwsLambdaController.class);

    private final DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    @Autowired
    private MoquetteServer moquetteServer;
    @Autowired
    private ActionLogService actionLogService;
    @Autowired
    private DeviceInfoService deviceInfoService;

    @ResponseBody
    @RequestMapping(value = "/ping", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> ping() {
        LOG.debug("Ping request received");
        String date = dateFormat.format(new Date());
        return new ResponseEntity<>(date, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/action", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ActionResponse handleActionRequest(@RequestBody ActionRequest actionRequest) throws JsonProcessingException {
        LOG.debug("Request received: {}", actionRequest);
        actionLogService.logActionRequest(actionRequest);
        DeviceInfo deviceInfo = deviceInfoService.findByName(actionRequest.getDeviceName());
        DeviceStateChangeRequest deviceStateChangeRequest = new DeviceStateChangeRequest();
        deviceStateChangeRequest.setDeviceId(deviceInfo.getDeviceId());
        deviceStateChangeRequest.setAction(actionRequest.getAction());
        moquetteServer.publish("consumers", actionRequest);
        return new ActionResponse();
    }
}
