package com.dfirago.jsh.rpi.service;

import com.dfirago.jsh.rpi.domain.DeviceInfo;

/**
 * Created by dmfi on 13/01/2017.
 */
public interface DeviceInfoService {

    DeviceInfo registerDevice(DeviceInfo deviceInfo);

    DeviceInfo findByName(String name);
}
