package com.dfirago.jsh.rpi.domain;

import javax.persistence.*;

/**
 * Created by dmfi on 13/01/2017.
 */
@Entity
@Table(name = "JSH_RPI_ACTION_LOG")
public class ActionLogEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String deviceName;
    private String action;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        return "ActionLogEntry{" +
                "id=" + id +
                ", deviceName='" + deviceName + '\'' +
                ", action='" + action + '\'' +
                '}';
    }
}
