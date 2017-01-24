package com.dfirago.jsh.rpi.bash.nmcli;

import com.dfirago.jsh.rpi.bash.BashCommand;
import com.dfirago.jsh.rpi.bash.ExecutionResult;
import com.dfirago.jsh.rpi.exception.CommandExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Created by dmfi on 24/01/2017.
 */
@Service
public class NetworkManager {

    private static final Logger LOG = LoggerFactory.getLogger(NetworkManager.class);

    private static final String SCAN_NETWORKS_COMMAND = "nmcli --fields SSID --terse device wifi list";
    private static final String CONNECT_OPEN_NETWORK_COMMAND = "nmcli device wifi connect %1$s name %1$s";
    private static final String CONNECT_NETWORK_COMMAND = "nmcli device wifi connect %1$s password %2$s name %1$s";
    private static final String GET_CONNECTIONS_COMMAND = "nmcli --fields NAME --terse connection show";
    private static final String GET_ACTIVE_CONNECTIONS_COMMAND = "nmcli --fields NAME --terse connection  show --active";
    private static final String CONNECTION_UP_COMMAND = "nmcli connection up %1$s";
    private static final String CONNECTION_DELETE_COMMAND = "nmcli connection delete %1$s";

    public List<String> scanNetworks() {
        LOG.debug("Scanning networks...");
        ExecutionResult executionResult = BashCommand.execute(SCAN_NETWORKS_COMMAND);
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

    public int connect(String ssid, String password) {
        ExecutionResult executionResult;
        if (password == null || password.length() == 0) {
            LOG.debug("No password provided, connecting to {} open network...", ssid);
            executionResult = BashCommand.execute(String.format(CONNECT_OPEN_NETWORK_COMMAND, ssid));
        } else {
            LOG.debug("Connecting to {} network using provided password", ssid);
            executionResult = BashCommand.execute(String.format(CONNECT_NETWORK_COMMAND, ssid, password));
        }
        LOG.debug("Command execution result:\n{}", executionResult);
        return executionResult.getExitCode();
    }

    public int enableConnection(String name) {
        LOG.debug("Starting connection up: {}", name);
        String command = String.format(CONNECTION_UP_COMMAND, name);
        ExecutionResult executionResult = BashCommand.execute(command);
        LOG.debug("Command execution result:\n{}", executionResult);
        return executionResult.getExitCode();
    }

    public int connectionDelete(String name) {
        LOG.debug("Deleting connection: {}", name);
        String command = String.format(CONNECTION_DELETE_COMMAND, name);
        ExecutionResult executionResult = BashCommand.execute(command);
        LOG.debug("Command execution result:\n{}", executionResult);
        return executionResult.getExitCode();
    }

    public List<String> getConnections() {
        LOG.debug("Retrieving connections list...");
        ExecutionResult executionResult = BashCommand.execute(GET_CONNECTIONS_COMMAND);
        if (executionResult.getExitCode() == 0) {
            String[] networks = executionResult.getMessage().split("\n");
            LOG.debug("Number of connections returned by the command: {}", networks.length);
            return Arrays.asList(networks);
        } else {
            LOG.error("Error occurred while executing shell command: {}", executionResult.getMessage());
            throw new CommandExecutionException(executionResult.getMessage());
        }
    }

    public List<String> getActiveConnections() {
        LOG.debug("Retrieving active connections list...");
        ExecutionResult executionResult = BashCommand.execute(GET_ACTIVE_CONNECTIONS_COMMAND);
        if (executionResult.getExitCode() == 0) {
            String[] networks = executionResult.getMessage().split("\n");
            LOG.debug("Number of active connections returned by the command: {}", networks.length);
            return Arrays.asList(networks);
        } else {
            LOG.error("Error occurred while executing shell command: {}", executionResult.getMessage());
            throw new CommandExecutionException(executionResult.getMessage());
        }
    }
}
