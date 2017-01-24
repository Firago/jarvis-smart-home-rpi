package com.dfirago.jsh.rpi.bash;

import com.google.common.base.Throwables;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * Created by dmfi on 13/01/2017.
 */
public final class BashCommand {

    private static final Logger LOG = LoggerFactory.getLogger(BashCommand.class);

    public static ExecutionResult execute(String command) {
        LOG.debug("Executing command: {}", command);
        try {
            Process process = Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", command});
            int exitCode = process.waitFor();
            LOG.debug("Exit code: {}", exitCode);
            String responseMessage;
            if (exitCode == 0) {
                InputStream processInputStream = process.getInputStream();
                responseMessage = IOUtils.toString(processInputStream, StandardCharsets.UTF_8);
            } else {
                InputStream processErrorStream = process.getErrorStream();
                responseMessage = IOUtils.toString(processErrorStream, StandardCharsets.UTF_8);
            }
            return new ExecutionResult(exitCode, responseMessage);
        } catch (IOException | InterruptedException e) {
            throw Throwables.propagate(e);
        }
    }
}
