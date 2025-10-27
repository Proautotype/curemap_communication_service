package com.custard.curemapcommunicationservice.infrastructure.storage.data;

import com.custard.curemapcommunicationservice.domain.enums.MessageDeliveryStatus;
import com.custard.curemapcommunicationservice.infrastructure.storage.model.ChatMessageEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaChatMessageRepository extends JpaRepository<ChatMessageEntity, String> {
    List<ChatMessageEntity> findByRecipientIdIgnoreCaseAndStatusOrderByTimestampAsc(String recipientId, MessageDeliveryStatus status, Sort sort);
}
