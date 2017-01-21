package com.dfirago.jsh.rpi.web.rest.api.model;

import java.util.List;

/**
 * Created by dmfi on 13/01/2017.
 */
public class ScanNetworksResponse {

    private List<String> devices;

    public List<String> getDevices() {
        return devices;
    }

    public void setDevices(List<String> devices) {
        this.devices = devices;
    }
}
