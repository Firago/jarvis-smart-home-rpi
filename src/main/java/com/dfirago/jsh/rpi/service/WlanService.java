package com.dfirago.jsh.rpi.service;

import java.io.IOException;
import java.util.List;

/**
 * Created by dmfi on 13/01/2017.
 */
public interface WlanService {

    List<String> scanNetworks() throws IOException, InterruptedException;
}
