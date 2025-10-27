package com.custard.curemapcommunicationservice.application.mapper;

import com.custard.curemapcommunicationservice.application.commands.CreatePostCommand;
import com.custard.curemapcommunicationservice.application.commands.ReplyPostCommand;
import com.custard.curemapcommunicationservice.domain.model.Comment;
import com.custard.curemapcommunicationservice.domain.model.Post;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {
    public Post toModel(CreatePostCommand command) {
        return Post.builder()
                .posterId(command.getPosterId())
                .content(command.getContent())
                .timestamp(command.getTimestamp())
                .mediaUrls(command.getMediaUrls())
                .build();
    }

    public Comment toCommentModel(ReplyPostCommand command) {
        return Comment.builder()
                .postId(command.getPostId())
                .content(command.getContent())
                .commentatorId(command.getCommentatorId())
                .mediaUrls(command.getMediaUrls())
                .replyTo(command.getReplyTo())
                .build();
    }
}
