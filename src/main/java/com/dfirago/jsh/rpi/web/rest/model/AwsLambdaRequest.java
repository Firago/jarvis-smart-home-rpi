package com.dfirago.jsh.rpi.web.rest.model;

/**
 * Created by dmfi on 13/01/2017.
 */
public class AwsLambdaRequest {

    private String deviceName;
    private String action;

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
