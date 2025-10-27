package com.custard.curemapcommunicationservice;

import com.corundumstudio.socketio.SocketIOServer;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SocketServerRunner implements CommandLineRunner {

    private final SocketIOServer server;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public SocketServerRunner(SocketIOServer server) {
        this.server = server;
    }

    @Override
    public void run(String... args) throws Exception {
        server.start();
        logger.info("Socket.IO server started on port {}", server.getConfiguration().getPort());
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.info("Stopping Socket.IO server...");
            server.stop();
        }));
    }

    @PreDestroy
    public void stopSocketServer() {
        if (server != null) {
            server.stop();
        }
    }
}
