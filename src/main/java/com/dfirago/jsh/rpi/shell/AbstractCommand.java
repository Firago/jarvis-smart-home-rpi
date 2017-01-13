package com.dfirago.jsh.rpi.shell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by dmfi on 13/01/2017.
 */
public abstract class AbstractCommand {

    public static String executeCommand(String command) throws IOException, InterruptedException {
        StringBuffer output = new StringBuffer();
        Process process = Runtime.getRuntime().exec(command);
        int exitCode = process.waitFor();
        if (exitCode == 0) {
            InputStream processInputStream = process.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(processInputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = reader.readLine())!= null) {
                output.append(line + "\n");
            }
        }
        return output.toString();
    }
}
