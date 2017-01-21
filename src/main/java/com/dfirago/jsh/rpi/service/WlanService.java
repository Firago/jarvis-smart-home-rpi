package com.dfirago.jsh.rpi.service;

import java.util.List;

/**
 * Created by dmfi on 13/01/2017.
 */
public interface WlanService {

    List<String> scanNetworks(boolean jshOnly);

    boolean connect(String ssid);

    boolean connect(String ssid, String password);
}
