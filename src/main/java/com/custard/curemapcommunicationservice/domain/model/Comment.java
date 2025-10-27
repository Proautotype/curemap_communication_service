package com.custard.curemapcommunicationservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private String commentatorId;
    private String postId;
    private String content;
    private String replyTo;
    private List<String> mediaUrls;
    private List<Comment> replies;
    private Instant timestamp;
    private Post post;

    private int likes = 0;
    private int dislikes = 0;
    private int flaggedCount = 0;
    private int views = 0;

    public int upLike() {
        return this.likes++;
    }

    public int unLike() {
        return this.likes--;
    }

    public int addDislike() {
        return this.dislikes++;
    }

    public int removeDislike() {
        return this.dislikes--;
    }

    public int addViews() {
        return this.views++;
    }
}
