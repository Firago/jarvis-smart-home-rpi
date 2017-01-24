package com.dfirago.jsh.rpi.bash.nmcli;

/**
 * Created by dmfi on 24/01/2017.
 */
public interface ExitCode {
    /**
     * indicates the operation succeeded.
     */
    int SUCCESS = 0;
    /**
     * Unknown or unspecified error.
     */
    int UNKNOWN_ERROR = 1;
    /**
     * Invalid user input, wrong nmcli invocation.
     */
    int INVALID_INPUT = 2;
    /**
     * Timeout expired (see --wait option).
     */
    int TIMEOUT_EXPIRED = 3;
    /**
     * Connection activation failed.
     */
    int CONNECTION_ACTIVATION_FAILED = 4;
    /**
     * Connection deactivation failed.
     */
    int CONNECTION_DEACTIVATION_FAILED = 5;
    /**
     * Disconnecting device failed.
     */
    int DISCONNECTING_DEVICE_FAILED = 6;
    /**
     * Connection deletion failed.
     */
    int CONNECTION_DELETION_FAILED = 7;
    /**
     * NetworkManager is not running.
     */
    int NETWORK_MANAGER_NOT_RUNNING = 8;
    /**
     * Connection, device, or access point does not exist.
     */
    int NOT_EXISTS_ERROR = 10;
}
