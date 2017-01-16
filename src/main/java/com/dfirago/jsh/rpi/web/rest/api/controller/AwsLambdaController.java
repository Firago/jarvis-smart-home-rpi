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
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by dmfi on 13/01/2017.
 */
@RestController
@RequestMapping("/api/aws")
public class AwsLambdaController extends AbstractController {

    private static final Logger LOG = LoggerFactory.getLogger(AwsLambdaController.class);

    @Autowired
    private MoquetteServer moquetteServer;
    @Autowired
    private ActionLogService actionLogService;
    @Autowired
    private DeviceInfoService deviceInfoService;

    @ResponseBody
    @RequestMapping(value = "/action", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ActionResponse handleActionRequest(@RequestBody ActionRequest actionRequest) throws JsonProcessingException {
        LOG.info("Request received: {}", actionRequest);
        actionLogService.logActionRequest(actionRequest);
        DeviceInfo deviceInfo = deviceInfoService.findByName(actionRequest.getDeviceName());
        DeviceStateChangeRequest deviceStateChangeRequest = new DeviceStateChangeRequest();
        deviceStateChangeRequest.setDeviceId(deviceInfo.getDeviceId());
        deviceStateChangeRequest.setAction(actionRequest.getAction());
        moquetteServer.publish("consumers", actionRequest);
        return new ActionResponse();
    }
}
