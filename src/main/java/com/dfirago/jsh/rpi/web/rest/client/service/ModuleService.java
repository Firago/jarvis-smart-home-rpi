package com.dfirago.jsh.rpi.web.rest.client.service;

import com.dfirago.jsh.rpi.config.Constants;
import com.dfirago.jsh.rpi.web.rest.client.model.ModuleInfoResponse;
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

    public ModuleInfoResponse getModuleInfo() {
        return restTemplate.getForObject(Constants.MODULE_HOST + "/module/info", ModuleInfoResponse.class);
    }
}
