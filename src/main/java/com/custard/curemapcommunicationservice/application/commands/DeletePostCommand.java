package com.custard.curemapcommunicationservice.application.commands;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeletePostCommand {
    private String postId;
    private String userId; // The ID of the user deleting the post
    private String reason; // Optional reason for deletion
}
