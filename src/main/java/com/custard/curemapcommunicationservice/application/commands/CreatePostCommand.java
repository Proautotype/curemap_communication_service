package com.custard.curemapcommunicationservice.application.commands;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class CreatePostCommand {
    @NotNull
    private String posterId;
    private List<String> mediaUrls;
    @NotNull(message = "title is missing")
    private String title;
    @NotNull(message = "content is missing")
    private String content;
    private Instant timestamp;
}
