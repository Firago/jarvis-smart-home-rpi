package com.dfirago.jsh.rpi.web.mqtt.interceptor;

import io.moquette.interception.AbstractInterceptHandler;
import io.moquette.interception.messages.InterceptPublishMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by dmfi on 13/01/2017.
 */
@Component
public class PublishedMessageHandler extends AbstractInterceptHandler {

    private static final Logger LOG = LoggerFactory.getLogger(PublishedMessageHandler.class);

    @Override
    public void onPublish(InterceptPublishMessage message) {
        LOG.info("Moquette MQTT broker message intercepted");
        LOG.info("Topic: {}", message.getTopicName());
        LOG.info("Content: {}", new String(message.getPayload().array()));
    }
}