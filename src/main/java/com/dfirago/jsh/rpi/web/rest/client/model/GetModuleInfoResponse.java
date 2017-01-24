package com.dfirago.jsh.rpi.web.rest.client.model;

/**
 * Created by dmfi on 13/01/2017.
 */
public class GetModuleInfoResponse {

    private String moduleId;

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    @Override
    public String toString() {
        return "GetModuleInfoResponse{" +
                "moduleId='" + moduleId + '\'' +
                '}';
    }
}
