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
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * Created by dmfi on 13/01/2017.
 */
@RestController
@RequestMapping("/api/mobile")
public class MobileController extends AbstractController {

    private static final Logger LOG = LoggerFactory.getLogger(MobileController.class);

    @Autowired
    private DeviceInfoService deviceInfoService;
    @Autowired
    private ModuleService moduleService;
    @Autowired
    private WlanService wlanService;

    @ResponseBody
    @RequestMapping(value = "/devices", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public FindAvailableDevicesResponse findAvailableDevices() throws IOException, InterruptedException {
        LOG.info("Scan network for available devices request received");
        List<String> devices = wlanService.scanNetworks();
        LOG.info("Number of networks available: {}", devices.size());
        FindAvailableDevicesResponse response = new FindAvailableDevicesResponse();
        response.setDevices(devices);
        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/devices", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public RegisterDeviceResponse registerDevice(@RequestBody RegisterDeviceRequest registerDeviceRequest) {
        LOG.info("Register new device request received: {}", registerDeviceRequest);
        DeviceInfo deviceInfo = new DeviceInfo();
        // TODO connect to registerDeviceRequest.getSsid() network using Command
        ModuleInfoResponse moduleInfo = moduleService.getModuleInfo();
        deviceInfo.setDeviceId(moduleInfo.getModuleId());
        deviceInfo.setName(registerDeviceRequest.getDeviceName());
        deviceInfoService.registerDevice(deviceInfo);
        return new RegisterDeviceResponse();
    }
}
