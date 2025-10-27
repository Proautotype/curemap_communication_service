package com.custard.curemapcommunicationservice.infrastructure.socket;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.custard.curemapcommunicationservice.adapter.socket.UserEvents;
import com.custard.curemapcommunicationservice.application.usecases.chatmessages.GetAllUserUnDeliveredMessagesUseCase;
import com.custard.curemapcommunicationservice.domain.ports.XRedisCacheService;
import com.custard.curemapcommunicationservice.shared.AppUtil;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class RegisterListeners {

    private final SocketIOServer server;
    private final XRedisCacheService cacheService;
    private final UserEvents userEvents;
    private final GetAllUserUnDeliveredMessagesUseCase getAllUserUnDeliveredMessagesUseCase;


    @PostConstruct()
    public void register() {
        server.addConnectListener(this::onConnect);
        server.addDisconnectListener(this::onDisconnect);
        server.addListeners(userEvents);
    }

    private void onConnect(SocketIOClient client) {
        String username = client.getHandshakeData().getSingleUrlParam("username");
        String cacheKey = AppUtil.ON_CONNECT_REDIS_CACHE_KEY.concat(username);
        cacheService.saveCache(cacheKey, client.getSessionId().toString());

        getAllUserUnDeliveredMessagesUseCase.execute(username);
    }

    private void onDisconnect(SocketIOClient client) {
        String username = client.getHandshakeData().getSingleUrlParam("username");
        String cacheKey = AppUtil.ON_CONNECT_REDIS_CACHE_KEY.concat(username);
        cacheService.delCache(cacheKey);
    }

}
