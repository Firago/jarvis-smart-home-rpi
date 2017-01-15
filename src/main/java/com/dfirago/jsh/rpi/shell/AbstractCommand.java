package com.dfirago.jsh.rpi.shell;

import com.dfirago.jsh.rpi.exception.CommandExecutionException;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * Created by dmfi on 13/01/2017.
 */
public abstract class AbstractCommand {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractCommand.class);

    public static String executeCommand(String command) throws IOException, InterruptedException {
        LOG.debug("Executing command: {}", command);
        Process process = Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", command});
        int exitCode = process.waitFor();
        LOG.debug("Exit code: {}", exitCode);
        if (exitCode == 0) {
            InputStream processInputStream = process.getInputStream();
            return IOUtils.toString(processInputStream, StandardCharsets.UTF_8);
        } else {
            InputStream processErrorStream = process.getErrorStream();
            String errorMessage = IOUtils.toString(processErrorStream, StandardCharsets.UTF_8);
            LOG.error("Error occurred while executing shell command: {}", errorMessage);
            throw new CommandExecutionException(errorMessage);
        }
    }
}
