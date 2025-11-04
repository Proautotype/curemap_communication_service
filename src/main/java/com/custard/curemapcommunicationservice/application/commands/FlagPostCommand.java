package com.custard.curemapcommunicationservice.application.commands;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FlagPostCommand {
    private String postId;
    private String userId;
    private String reason; // Optional reason for flagging
}
