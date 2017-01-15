package com.dfirago.jsh.rpi.exception;

/**
 * Created by dmfi on 15/01/2017.
 */
public class CommandExecutionException extends RuntimeException {

    public CommandExecutionException(String message) {
        super(message);
    }

    public CommandExecutionException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommandExecutionException(Throwable cause) {
        super(cause);
    }
}
