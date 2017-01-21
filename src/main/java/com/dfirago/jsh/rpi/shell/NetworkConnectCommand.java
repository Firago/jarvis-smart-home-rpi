package com.dfirago.jsh.rpi.shell;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by dmfi on 13/01/2017.
 */
public class NetworkConnectCommand extends AbstractCommand {

    private static final Logger LOG = LoggerFactory.getLogger(NetworkConnectCommand.class);

    private static final String NETWORK_CONNECT_COMMAND = "nmcli dev wifi connect %s";
    private static final String WITH_PASSWORD = " password %s";

    private static final long CONNECTION_TIMEOUT = 20 * 1000; //20 sec

    public static boolean execute(String ssid, String password) {
        LOG.debug("Executing NetworkConnectCommand...");
        long timeout = System.currentTimeMillis() + CONNECTION_TIMEOUT;

        do {
            ExecutionResult executionResult = executeCommand(buildCommand(ssid, password));
            LOG.debug("Command execution result:\n{}", executionResult);
            if (executionResult.getExitCode() == 0) {
                LOG.debug("Connection to {} network established successfully", ssid);
                return true;
            }
        } while (System.currentTimeMillis() < timeout);

        LOG.warn("Connection to {} failed: timeout", ssid);

        return false;
    }

    private static String buildCommand(String ssid, String password) {
        String connectCommand = String.format(NETWORK_CONNECT_COMMAND, ssid);
        if (password != null && !password.isEmpty()) {
            String withPassword = String.format(WITH_PASSWORD, password);
            connectCommand += withPassword;
        }
        return connectCommand;
    }
}
