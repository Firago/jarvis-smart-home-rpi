package com.dfirago.jsh.rpi.exception;

/**
 * Created by dmfi on 13/01/2017.
 */
public class DeviceInfoNotFoundException extends RuntimeException {

    public DeviceInfoNotFoundException(String message) {
        super(message);
    }

    public DeviceInfoNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeviceInfoNotFoundException(Throwable cause) {
        super(cause);
    }
}
