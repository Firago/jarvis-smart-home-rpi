package com.dfirago.jsh.rpi.service;

import com.dfirago.jsh.rpi.domain.DeviceInfo;

import java.util.List;

/**
 * Created by dmfi on 13/01/2017.
 */
public interface DeviceInfoService {

    DeviceInfo registerDevice(DeviceInfo deviceInfo);

    DeviceInfo findByName(String name);

    List<DeviceInfo> list();
}
