package com.custard.curemapcommunicationservice.application.commands;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdatePostReactionCommand {
    private String postId;
    private String userId;
    private ReactionType reactionType; // LIKE, DISLIKE
    private boolean isAdding; // true to add, false to remove

    public enum ReactionType {
        LIKE, DISLIKE
    }
}
