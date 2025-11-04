package com.custard.curemapcommunicationservice.domain.model;

import lombok.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private String id;
    private String commentatorId;
    private String postId;
    private String content;
    private String replyTo;

    @Builder.Default
    private List<String> mediaUrls = new ArrayList<>();

    @Builder.Default
    private List<Comment> replies = new ArrayList<>();

    private Instant timestamp;
    private Post post;

    @Builder.Default
    private int likes = 0;

    @Builder.Default
    private int dislikes = 0;

    @Builder.Default
    private int flaggedCount = 0;

    @Builder.Default
    private int views = 0;

    public int upLike() {
        return ++this.likes;  // Changed to pre-increment for atomic behavior
    }

    public int unLike() {
        return this.likes > 0 ? --this.likes : 0;  // Prevent negative likes
    }

    public int addDislike() {
        return ++this.dislikes;  // Changed to pre-increment for atomic behavior
    }

    public int removeDislike() {
        return this.dislikes > 0 ? --this.dislikes : 0;  // Prevent negative dislikes
    }

    public int addViews() {
        return ++this.views;  // Changed to pre-increment for atomic behavior
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id='" + id + '\'' +
                ", commentatorId='" + commentatorId + '\'' +
                ", postId='" + postId + '\'' +
                ", content='" + content + '\'' +
                ", replyTo='" + replyTo + '\'' +
                ", mediaUrls=" + mediaUrls.size() + " items" +
                ", replies=" + replies.size() + " replies" +
                ", timestamp=" + timestamp +
                ", likes=" + likes +
                ", dislikes=" + dislikes +
                ", flaggedCount=" + flaggedCount +
                ", views=" + views +
                '}';
    }
}