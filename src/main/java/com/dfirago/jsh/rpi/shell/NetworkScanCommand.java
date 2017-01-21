package com.dfirago.jsh.rpi.shell;

import com.dfirago.jsh.rpi.exception.CommandExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

/**
 * Created by dmfi on 13/01/2017.
 */
public class NetworkScanCommand extends AbstractCommand {

    private static final Logger LOG = LoggerFactory.getLogger(NetworkScanCommand.class);

    private static final String SCAN_NETWORKS_COMMAND = "sudo iwlist wlan1 scan";
    private static final String ONLY_SSID = " | grep -oP '(?<=ESSID:\")\\w+(?=\")'";

    public static List<String> execute() {
        LOG.debug("Executing NetworkScanCommand...");
        ExecutionResult executionResult = executeCommand(SCAN_NETWORKS_COMMAND + ONLY_SSID);
        LOG.debug("Command execution result:\n{}", executionResult);
        if (executionResult.getExitCode() == 0) {
            String[] networks = executionResult.getMessage().split("\n");
            LOG.debug("Number of networks returned by the command: {}", networks.length);
            return Arrays.asList(networks);
        } else {
            LOG.error("Error occurred while executing shell command: {}", executionResult.getMessage());
            throw new CommandExecutionException(executionResult.getMessage());
        }
    }
}
