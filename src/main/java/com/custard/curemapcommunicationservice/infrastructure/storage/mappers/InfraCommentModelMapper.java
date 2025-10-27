package com.custard.curemapcommunicationservice.infrastructure.storage.mappers;

import com.custard.curemapcommunicationservice.domain.model.Comment;
import com.custard.curemapcommunicationservice.infrastructure.storage.model.CommentEntity;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

@Component
public class InfraCommentModelMapper {

    @Transactional
    public Comment toModel(CommentEntity entity) {
        return Comment.builder()
                .commentatorId(entity.getCommentatorId())
                .postId(entity.getPostEntity().getId())
                .content(entity.getContent())
                .replies(entity.getReplies()
                        .stream().map(
                                existingEntity ->
                                        Comment.builder()
                                                .commentatorId(existingEntity.getCommentatorId())
                                                .postId(existingEntity.getId())
                                                .content(existingEntity.getContent())
                                                .likes(existingEntity.getLikes())
                                                .dislikes(existingEntity.getDislikes())
                                                .flaggedCount(existingEntity.getFlaggedCount())
                                                .views(existingEntity.getViews())
                                                .build())
                        .toList())
                .mediaUrls(entity.getMediaUrls())
                .likes(entity.getLikes())
                .dislikes(entity.getDislikes())
                .flaggedCount(entity.getFlaggedCount())
                .views(entity.getViews())
                .build();
    }

    public CommentEntity toEntity(Comment model) {
        CommentEntity entity = new CommentEntity();
        entity.setCommentatorId(model.getCommentatorId());
        entity.setContent(model.getContent());
        return entity;
    }
}
