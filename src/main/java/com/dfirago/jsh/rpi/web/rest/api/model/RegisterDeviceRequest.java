package com.dfirago.jsh.rpi.web.rest.api.model;

/**
 * Created by dmfi on 13/01/2017.
 */
public class RegisterDeviceRequest {

    private String deviceSsid;
    private String deviceName;

    public String getDeviceSsid() {
        return deviceSsid;
    }

    public void setDeviceSsid(String deviceSsid) {
        this.deviceSsid = deviceSsid;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    @Override
    public String toString() {
        return "RegisterDeviceRequest{" +
                "deviceSsid='" + deviceSsid + '\'' +
                ", deviceName='" + deviceName + '\'' +
                '}';
    }
}
