package com.dfirago.jsh.rpi.shell;

/**
 * Created by dmfi on 21/01/2017.
 */
public class ExecutionResult {

    private final int exitCode;
    private final String message;

    public ExecutionResult(int exitCode, String message) {
        this.exitCode = exitCode;
        this.message = message;
    }

    public int getExitCode() {
        return exitCode;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ExecutionResult{" +
                "exitCode=" + exitCode +
                ", message='" + message + '\'' +
                '}';
    }
}