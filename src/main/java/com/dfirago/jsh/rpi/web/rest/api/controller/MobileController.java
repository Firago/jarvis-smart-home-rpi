package com.dfirago.jsh.rpi.web.rest.api.controller;

import com.dfirago.jsh.rpi.domain.DeviceInfo;
import com.dfirago.jsh.rpi.service.DeviceInfoService;
import com.dfirago.jsh.rpi.service.WlanService;
import com.dfirago.jsh.rpi.web.rest.api.model.FindAvailableDevicesResponse;
import com.dfirago.jsh.rpi.web.rest.api.model.RegisterDeviceRequest;
import com.dfirago.jsh.rpi.web.rest.api.model.RegisterDeviceResponse;
import com.dfirago.jsh.rpi.web.rest.client.model.ModuleInfoResponse;
import com.dfirago.jsh.rpi.web.rest.client.service.ModuleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by dmfi on 13/01/2017.
 */
@RestController
@RequestMapping("/mobile")
public class MobileController {

    private static final Logger LOG = LoggerFactory.getLogger(MobileController.class);

    private final DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy hh:mm:ss");

    @Autowired
    private DeviceInfoService deviceInfoService;
    @Autowired
    private ModuleService moduleService;
    @Autowired
    private WlanService wlanService;

    @ResponseBody
    @RequestMapping(value = "/ping", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> ping() {
        LOG.debug("Ping request received");
        String date = dateFormat.format(new Date());
        return new ResponseEntity<>(date, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/devices", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public FindAvailableDevicesResponse findAvailableDevices() throws IOException, InterruptedException {
        LOG.debug("Scan network for available devices request received");
        List<String> devices = wlanService.scanNetworks();
        FindAvailableDevicesResponse response = new FindAvailableDevicesResponse();
        response.setDevices(devices);
        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/devices", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public RegisterDeviceResponse registerDevice(@RequestBody RegisterDeviceRequest registerDeviceRequest) {
        LOG.debug("Register new device request received: {}", registerDeviceRequest);
        DeviceInfo deviceInfo = new DeviceInfo();
        // TODO connect to registerDeviceRequest.getSsid() network using Command
        ModuleInfoResponse moduleInfo = moduleService.getModuleInfo();
        deviceInfo.setDeviceId(moduleInfo.getModuleId());
        deviceInfo.setName(registerDeviceRequest.getDeviceName());
        deviceInfoService.registerDevice(deviceInfo);
        return new RegisterDeviceResponse();
    }
}
