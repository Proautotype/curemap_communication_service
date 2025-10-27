package com.custard.curemapcommunicationservice.application.commands;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
public class CreatePostCommand {
    private String posterId;
    private List<String> mediaUrls;
    private String content;
    private Instant timestamp;
    private boolean isDeleted;
}
