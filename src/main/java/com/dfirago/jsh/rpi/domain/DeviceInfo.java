package com.dfirago.jsh.rpi.domain;

import javax.persistence.*;

/**
 * Created by dmfi on 13/01/2017.
 */
@Entity
@Table(name = "JSH_RPI_DEVICE_INFO")
public class DeviceInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String deviceId;
    private String ssid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    @Override
    public String toString() {
        return "DeviceInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", ssid='" + ssid + '\'' +
                '}';
    }
}
