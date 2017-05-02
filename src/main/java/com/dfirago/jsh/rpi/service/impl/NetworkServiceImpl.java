package com.dfirago.jsh.rpi.service.impl;

import com.dfirago.jsh.rpi.bash.nmcli.ExitCode;
import com.dfirago.jsh.rpi.bash.nmcli.NetworkManager;
import com.dfirago.jsh.rpi.service.NetworkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmfi on 13/01/2017.
 */
@Service
public class NetworkServiceImpl implements NetworkService {

    private static final Logger LOG = LoggerFactory.getLogger(NetworkServiceImpl.class);

    private static final String JSH_NETWORK_FORMAT = "^JSH_.*$";

    private static final long ENABLE_CONNECTION_TIMEOUT = 10 * 1000; // 10 sec

    @Autowired
    private NetworkManager networkManager;

    @Override
    public List<String> scanNetworks(boolean jshOnly) {
        // need to convert List to ArrayList in order to filter it using predicate
        ArrayList<String> networks = new ArrayList<>(networkManager.scanNetworks());
        LOG.info("Available networks: {}", networks);
        if (jshOnly) {
            networks.removeIf(s -> !s.matches(JSH_NETWORK_FORMAT) || s.equals("JSH_HUB1")); // TODO retrieve name from env vars
            LOG.info("Non JSH networks will be skipped. Available JSH devices: {}", networks);
        }
        return networks;
    }

    @Override
    public boolean connect(String name) {
        return connect(name, null);
    }

    @Override
    public boolean connect(String name, String password) {
        boolean connected = checkExistingConnections(name);
        if (!connected) {
            int exitCode = networkManager.connect(name, password);
            switch (exitCode) {
                case ExitCode.SUCCESS:
                    connected = true;
                    break;
                case ExitCode.CONNECTION_ACTIVATION_FAILED:
                    connected = retryEnableConnection(name, ENABLE_CONNECTION_TIMEOUT);
                    break;
            }
        }
        return connected;
    }

    private boolean checkExistingConnections(String name) {
        boolean connected = false;
        LOG.debug("Verifying if connection with name {} already exists...", name);
        List<String> connections = networkManager.getConnections();
        if (connections.contains(name)) {
            LOG.debug("Connection already exists, enabling...");
            int exitCode = networkManager.enableConnection(name);
            if (exitCode == ExitCode.SUCCESS) {
                LOG.debug("Connection enabled successfully");
                connected = true;
            } else {
                LOG.debug("Failed to enable connection, it'll be replaced with a new one");
                networkManager.connectionDelete(name);
            }
        }
        return connected;
    }

    private boolean retryEnableConnection(String name, long retryTimeout) {
        long end = System.currentTimeMillis() + retryTimeout;
        while (System.currentTimeMillis() < end) {
            if (networkManager.enableConnection(name) == ExitCode.SUCCESS) {
                LOG.debug("Connection to {} network established successfully", name);
                return true;
            }
        }
        return false;
    }

    @Override
    public String getActiveConnection() {
        List<String> activeConnections = networkManager.getActiveConnections();
        LOG.info("Active connections: {}", activeConnections);
        return activeConnections.iterator().next();
    }
}
