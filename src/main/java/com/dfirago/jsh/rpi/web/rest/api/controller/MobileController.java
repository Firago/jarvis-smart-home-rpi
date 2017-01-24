package com.dfirago.jsh.rpi.web.rest.api.controller;

import com.dfirago.jsh.rpi.domain.DeviceInfo;
import com.dfirago.jsh.rpi.service.DeviceInfoService;
import com.dfirago.jsh.rpi.service.NetworkService;
import com.dfirago.jsh.rpi.web.rest.api.model.*;
import com.dfirago.jsh.rpi.web.rest.client.model.DeviceConfigurationChangeRequest;
import com.dfirago.jsh.rpi.web.rest.client.model.GetModuleInfoResponse;
import com.dfirago.jsh.rpi.web.rest.client.service.ModuleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    private NetworkService networkService;

    @ResponseBody
    @RequestMapping(value = "/networks", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ScanNetworksResponse scanNetworks() {
        LOG.info("Scan network for available networks request received");
        List<String> devices = networkService.scanNetworks(false);
        LOG.info("Number of networks available: {}", devices.size());
        ScanNetworksResponse response = new ScanNetworksResponse();
        response.setDevices(devices);
        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/networks", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public NetworkConnectResponse networkConnect(@RequestBody NetworkConnectRequest request) {
        NetworkConnectResponse response = new NetworkConnectResponse();
        LOG.info("Connect to specified network request received");
        if (networkService.connect(request.getSsid(), request.getPassword())) {
            LOG.debug("Successfully connected to {}", request.getSsid());
            response.setSuccess(true);
        } else {
            LOG.debug("Connection to {} failed", request.getSsid());
            response.setSuccess(false);
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/networks/jsh", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ScanNetworksResponse findJshNetworks() {
        LOG.info("Scan network for available JSH devices request received");
        List<String> devices = networkService.scanNetworks(true);
        LOG.info("Number of JSH devices available: {}", devices.size());
        ScanNetworksResponse response = new ScanNetworksResponse();
        response.setDevices(devices);
        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/devices", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DeviceInfo>> getDevices() {
        LOG.info("Get registered JSH devices request received");
        List<DeviceInfo> devices = deviceInfoService.list();
        LOG.info("Number of registered devices: {}", devices.size());
        return ResponseEntity.ok(devices);
    }

    @ResponseBody
    @RequestMapping(value = "/devices", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public RegisterDeviceResponse registerDevice(@RequestBody RegisterDeviceRequest request) {
        LOG.info("Register new JSH device request received: {}", request);
        RegisterDeviceResponse response = new RegisterDeviceResponse();
        String activeConnection = networkService.getActiveConnection();
        if (networkService.connect(request.getDeviceSsid())) {
            LOG.debug("Connected to {}", request.getDeviceSsid());
            // TODO send SSID and password
            DeviceConfigurationChangeRequest deviceConfigurationChangeRequest
                    = new DeviceConfigurationChangeRequest();
            deviceConfigurationChangeRequest.setSsid("JSH_HUB1");
            deviceConfigurationChangeRequest.setPassword("raspberry");
            moduleService.changeDeviceConfiguration(deviceConfigurationChangeRequest);
            GetModuleInfoResponse moduleInfo = moduleService.getModuleInfo();
            LOG.debug("Device information retrieved: {}", moduleInfo);
            DeviceInfo deviceInfo = new DeviceInfo();
            deviceInfo.setDeviceId(moduleInfo.getModuleId());
            deviceInfo.setName(request.getDeviceName());
            deviceInfoService.registerDevice(deviceInfo);
            response.setSuccess(true);
        } else {
            response.setSuccess(false);
        }
        networkService.connect(activeConnection);
        return response;
    }
}
