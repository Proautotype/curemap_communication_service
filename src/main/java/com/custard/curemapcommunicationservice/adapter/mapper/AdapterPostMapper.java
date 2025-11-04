package com.custard.curemapcommunicationservice.adapter.mapper;

import com.custard.curemapcommunicationservice.adapter.dto.PostDto;
import com.custard.curemapcommunicationservice.adapter.dto.SimpleCommentDto;
import com.custard.curemapcommunicationservice.domain.model.Comment;
import com.custard.curemapcommunicationservice.domain.model.Post;
import org.springframework.stereotype.Component;

@Component
public class AdapterPostMapper {

    public PostDto toPostDto(Post post) {
        return PostDto.builder()
                .id(post.getId())
                .flaggedCount(post.getFlaggedCount())
                .likes(post.getLikes())
                .dislikes(post.getDislikes())
                .views(post.getViews())
                .posterId(post.getPosterId())
                .mediaUrls(post.getMediaUrls())
                .timestamp(post.getTimestamp())
                .content(post.getContent())
                .comments(post.getComments()
                        .stream()
                        .map(this::toSimpleCommentDto)
                        .toList()
                )
                .build();
    }

    public SimpleCommentDto toSimpleCommentDto(Comment comment) {
        return SimpleCommentDto.builder()
                .id(comment.getId())
                .commentator(comment.getCommentatorId())
                .content(comment.getContent())
                .lastTimeUpdated(comment.getTimestamp())
                .build();
    }

}
