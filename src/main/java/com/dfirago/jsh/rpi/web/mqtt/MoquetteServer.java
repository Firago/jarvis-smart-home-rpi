package com.dfirago.jsh.rpi.web.mqtt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.moquette.interception.InterceptHandler;
import io.moquette.parser.proto.messages.PublishMessage;
import io.moquette.server.Server;
import io.moquette.server.config.MemoryConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Properties;

import static io.moquette.parser.proto.messages.AbstractMessage.QOSType;

/**
 * Created by dmfi on 12/01/2017.
 */
@Service
public class MoquetteServer {

    private static final Logger LOG = LoggerFactory.getLogger(MoquetteServer.class);

    @Resource(name = "moquetteProperties")
    private Properties moquetteProperties;
    @Autowired
    private ObjectMapper jacksonObjectMapper;
    @Autowired
    private List<InterceptHandler> interceptors;

    private Server moquetteServer;

    private boolean running;

    @PostConstruct
    private void initialize() throws IOException {
        this.start();
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                MoquetteServer.this.stop();
            }
        });
    }

    public void start() throws IOException {
        LOG.info("Starting Moquette Server...");
        moquetteServer = new Server();
        LOG.info("Host: {}", moquetteProperties.getProperty("host"));
        LOG.info("Port: {}", moquetteProperties.getProperty("port"));
        LOG.info("WebSocket port: {}", moquetteProperties.getProperty("websocket_port"));
        final MemoryConfig config = new MemoryConfig(moquetteProperties);
        moquetteServer.startServer(config, interceptors);
        LOG.info("Moquette Server started successfully");
        running = true;
    }

    public void stop() {
        LOG.info("Stopping Moquette Server...");
        moquetteServer.stopServer();
        LOG.info("Moquette Server stopped successfully");
        running = false;
    }

    public boolean isRunning() {
        return running;
    }

    public void publish(String topic, Object payload) throws JsonProcessingException {
        byte[] bytes = jacksonObjectMapper.writeValueAsBytes(payload);
        PublishMessage message = new PublishMessage();
        message.setQos(QOSType.EXACTLY_ONCE);
        message.setPayload(ByteBuffer.wrap(bytes));
        message.setTopicName(topic);
        moquetteServer.internalPublish(message);
    }
}
