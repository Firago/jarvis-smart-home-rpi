package com.dfirago.jsh.rpi.service;

import com.dfirago.jsh.rpi.domain.ActionLogEntry;
import com.dfirago.jsh.rpi.web.rest.api.model.ActionRequest;

/**
 * Created by dmfi on 13/01/2017.
 */
public interface ActionLogService {

    ActionLogEntry logActionRequest(ActionRequest request);
}
