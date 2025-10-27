package com.custard.curemapcommunicationservice.infrastructure.config;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.Configuration;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class SocketConfiguration {

    @Bean
    public SocketIOServer socketIOServer() {
        Configuration socketConfig = new Configuration();
        socketConfig.setHostname("0.0.0.0");
        socketConfig.setPort(9092);
        socketConfig.setOrigin("*");
        return new SocketIOServer(socketConfig);
    }

}
