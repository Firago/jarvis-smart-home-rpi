package com.dfirago.jsh.rpi.web.rest.client.service;

import com.dfirago.jsh.rpi.config.Constants;
import com.dfirago.jsh.rpi.web.rest.client.model.DeviceConfigurationChangeRequest;
import com.dfirago.jsh.rpi.web.rest.client.model.DeviceConfigurationChangeResponse;
import com.dfirago.jsh.rpi.web.rest.client.model.GetModuleInfoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by dmfi on 13/01/2017.
 */
@Service
public class ModuleService {

    @Autowired
    private RestTemplate restTemplate;

    public GetModuleInfoResponse getModuleInfo() {
        return restTemplate.getForObject(Constants.MODULE_HOST + "/module/info", GetModuleInfoResponse.class);
    }

    public DeviceConfigurationChangeResponse changeDeviceConfiguration(DeviceConfigurationChangeRequest request) {
        return restTemplate.postForObject(
                Constants.MODULE_HOST + "/configuration/network", request, DeviceConfigurationChangeResponse.class);
    }
}
