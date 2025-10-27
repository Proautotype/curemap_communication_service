package com.custard.curemapcommunicationservice.application.commands;

import lombok.*;

import java.util.List;

@Setter
@Getter
public class ReplyPostCommand {
    private String postId;
    private String commentatorId;
    private String content;
    private List<String> mediaUrls;
    private String replyTo;
}
