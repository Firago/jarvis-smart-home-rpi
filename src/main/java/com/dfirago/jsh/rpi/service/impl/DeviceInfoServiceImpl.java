package com.dfirago.jsh.rpi.service.impl;

import com.dfirago.jsh.rpi.domain.DeviceInfo;
import com.dfirago.jsh.rpi.repository.DeviceInfoRepository;
import com.dfirago.jsh.rpi.service.DeviceInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dmfi on 13/01/2017.
 */
@Service
public class DeviceInfoServiceImpl implements DeviceInfoService {

    private static final Logger LOG = LoggerFactory.getLogger(DeviceInfoServiceImpl.class);

    @Autowired
    private DeviceInfoRepository deviceInfoRepository;

    @Override
    public DeviceInfo registerDevice(DeviceInfo deviceInfo) {
        LOG.debug("Adding new device: {}", deviceInfo);
        return deviceInfoRepository.save(deviceInfo);
    }

    @Override
    public DeviceInfo findByName(String name) {
        LOG.debug("Searching device by name: {}", name);
        return deviceInfoRepository.findByName(name);
    }

    @Override
    public DeviceInfo findBySsid(String ssid) {
        LOG.debug("Searching device by SSID: {}", ssid);
        return deviceInfoRepository.findBySsid(ssid);
    }

    @Override
    public List<DeviceInfo> list() {
        LOG.debug("Listing all devices...");
        return deviceInfoRepository.findAll();
    }

    @Override
    public void delete(DeviceInfo deviceInfo) {
        LOG.debug("Delete: {}", deviceInfo);
        deviceInfoRepository.delete(deviceInfo);
    }
}
