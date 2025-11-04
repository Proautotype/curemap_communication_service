package com.custard.curemapcommunicationservice.application.commands;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdatePostCommand {
    private String postId;
    private String userId;
    private String content;
}
