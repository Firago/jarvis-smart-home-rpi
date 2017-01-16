package com.dfirago.jsh.rpi.service.impl;

import com.dfirago.jsh.rpi.service.WlanService;
import com.dfirago.jsh.rpi.shell.NetworkScanCommand;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * Created by dmfi on 13/01/2017.
 */
@Service
public class WlanServiceImpl implements WlanService {

    @Override
    public List<String> scanNetworks() throws IOException, InterruptedException {
        return NetworkScanCommand.execute();
    }
}
