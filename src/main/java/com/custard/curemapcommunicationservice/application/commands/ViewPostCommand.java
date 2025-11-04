package com.custard.curemapcommunicationservice.application.commands;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ViewPostCommand {
    private String postId;
    private String viewerId; // The ID of the user viewing the post
}
