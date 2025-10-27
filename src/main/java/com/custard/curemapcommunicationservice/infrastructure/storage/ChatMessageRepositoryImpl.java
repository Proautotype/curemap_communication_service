package com.custard.curemapcommunicationservice.infrastructure.storage;

import com.custard.curemapcommunicationservice.application.exceptions.EntityNotFoundException;
import com.custard.curemapcommunicationservice.domain.enums.MessageDeliveryStatus;
import com.custard.curemapcommunicationservice.domain.model.ChatMessage;
import com.custard.curemapcommunicationservice.domain.ports.ChatMessageRepository;
import com.custard.curemapcommunicationservice.infrastructure.storage.data.JpaChatMessageRepository;
import com.custard.curemapcommunicationservice.infrastructure.storage.mappers.InfraChatMessageModelMapper;
import com.custard.curemapcommunicationservice.infrastructure.storage.model.ChatMessageEntity;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatMessageRepositoryImpl implements ChatMessageRepository {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    private final JpaChatMessageRepository jpaChatMessageRepository;
    private final InfraChatMessageModelMapper modelMapper;

    @Override
    @Transactional
    public void saveMessage(ChatMessage message) {
        try {
            ChatMessageEntity entity = modelMapper.toEntity(message);
            jpaChatMessageRepository.save(entity);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public void updateDeliveryStatus(String messageId, MessageDeliveryStatus deliveryStatus) {
        try{
            ChatMessageEntity chatMessage = findByMessageId(messageId);
            chatMessage.setStatus(deliveryStatus);
            jpaChatMessageRepository.save(chatMessage);
        } catch (RuntimeException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public List<ChatMessage> findByUserIdAndStatus(String userId, MessageDeliveryStatus status) {
        return jpaChatMessageRepository
                .findByRecipientIdIgnoreCaseAndStatusOrderByTimestampAsc
                        (userId, status, Sort.by(Sort.Direction.DESC))
                .stream()
                .map(modelMapper::toModel)
                .toList();
    }

    @Override
    @Transactional
    public ChatMessage getByMessageId(String messageId) {
        var messageEntity = this.findByMessageId(messageId);
        return modelMapper.toModel(messageEntity);
    }

    private ChatMessageEntity findByMessageId(String messageId) {
        return jpaChatMessageRepository.findById(messageId).orElseThrow(() -> new EntityNotFoundException("Message does not exist"));
    }
}
