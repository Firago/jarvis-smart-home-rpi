package com.dfirago.jsh.rpi.shell;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by dmfi on 13/01/2017.
 */
public class ScanNetworksCommand extends AbstractCommand {

    private static final String SCAN_NETWORKS_COMMAND = "sudo iwlist wlan1 scan";
    private static final String ONLY_SSID = " | grep -oP '(?<=ESSID:\")\\w+(?=\")'";

    public static List<String> execute() throws IOException, InterruptedException {
        String commandResult = executeCommand(SCAN_NETWORKS_COMMAND + ONLY_SSID);
        String[] networks = commandResult.split("\n");
        return Arrays.asList(networks);
    }
}
