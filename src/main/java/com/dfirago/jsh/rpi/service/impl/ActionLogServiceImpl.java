package com.dfirago.jsh.rpi.service.impl;

import com.dfirago.jsh.rpi.domain.ActionLogEntry;
import com.dfirago.jsh.rpi.repository.ActionLogRepository;
import com.dfirago.jsh.rpi.service.ActionLogService;
import com.dfirago.jsh.rpi.web.rest.api.model.ActionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dmfi on 13/01/2017.
 */
@Service
public class ActionLogServiceImpl implements ActionLogService {

    @Autowired
    private ActionLogRepository actionLogRepository;

    @Override
    public ActionLogEntry logActionRequest(ActionRequest request) {
        ActionLogEntry actionLogEntry = new ActionLogEntry();
        actionLogEntry.setDeviceName(request.getDeviceName());
        actionLogEntry.setAction(request.getAction());
        return actionLogRepository.save(actionLogEntry);
    }
}
