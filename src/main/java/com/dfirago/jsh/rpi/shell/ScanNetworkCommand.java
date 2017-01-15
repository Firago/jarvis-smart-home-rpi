package com.dfirago.jsh.rpi.shell;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by dmfi on 13/01/2017.
 */
public class ScanNetworkCommand extends AbstractCommand {

    private static final Logger LOG = LoggerFactory.getLogger(ScanNetworkCommand.class);

    private static final String SCAN_NETWORKS_COMMAND = "sudo iwlist wlan1 scan";
    private static final String ONLY_SSID = " | grep -oP '(?<=ESSID:\")\\w+(?=\")'";

    public static List<String> execute() throws IOException, InterruptedException {
        LOG.debug("Executing ScanNetworkCommand...");
        String commandResult = executeCommand(SCAN_NETWORKS_COMMAND + ONLY_SSID);
        LOG.debug("Command execution result:\n{}", commandResult);
        String[] networks = commandResult.split("\n");
        LOG.debug("Number of networks returned by the command: {}", networks.length);
        return Arrays.asList(networks);
    }
}
