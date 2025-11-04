package com.custard.curemapcommunicationservice.application.commands;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class CreateCommentCommand {
    @NotNull(message = "commentator not provided")
    private String commentatorId;
    @NotNull(message = "can't post empty content")
    private String content;
    private List<String> mediaUrls;
    private String replyTo;

    @Override
    public String toString() {
        return "CommentCommand{" +
                "commentatorId='" + commentatorId + '\'' +
                ", content='" + content + '\'' +
                ", mediaUrls=" + mediaUrls +
                ", replyTo='" + replyTo + '\'' +
                '}';
    }
}
