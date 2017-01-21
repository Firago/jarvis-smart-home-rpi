package com.dfirago.jsh.rpi.service.impl;

import com.dfirago.jsh.rpi.service.WlanService;
import com.dfirago.jsh.rpi.shell.NetworkConnectCommand;
import com.dfirago.jsh.rpi.shell.NetworkScanCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmfi on 13/01/2017.
 */
@Service
public class WlanServiceImpl implements WlanService {

    private static final Logger LOG = LoggerFactory.getLogger(WlanServiceImpl.class);

    private static final String JSH_NETWORK_FORMAT = "^JSH_.*$";

    @Override
    public List<String> scanNetworks(boolean jshOnly) {
        // need to convert List to ArrayList in order to filter it using predicate
        ArrayList<String> networks = new ArrayList<>(NetworkScanCommand.execute());
        LOG.info("Available networks: {}", networks);
        if (jshOnly) {
            networks.removeIf(s -> !s.matches(JSH_NETWORK_FORMAT));
            LOG.info("Non JSH networks will be skipped. Available JSH devices: {}", networks);
        }
        return networks;
    }

    @Override
    public boolean connect(String ssid) {
        return connect(ssid, null);
    }

    @Override
    public boolean connect(String ssid, String password) {
        return NetworkConnectCommand.execute(ssid, password);
    }
}
