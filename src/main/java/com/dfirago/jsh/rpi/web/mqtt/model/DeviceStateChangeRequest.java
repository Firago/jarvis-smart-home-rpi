package com.dfirago.jsh.rpi.web.mqtt.model;

/**
 * Created by dmfi on 13/01/2017.
 */
public class DeviceStateChangeRequest {

    private String deviceId;
    private String action;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
