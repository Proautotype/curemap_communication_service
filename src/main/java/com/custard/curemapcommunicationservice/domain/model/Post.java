package com.custard.curemapcommunicationservice.domain.model;

import lombok.*;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Builder
public class Post {
    private String posterId;
    private List<String> mediaUrls;
    private String content;
    private Instant timestamp;
    private boolean isDeleted;
    private List<Comment> comments;

    private int likes = 0;
    private int dislikes = 0;
    private int flaggedCount = 0;
    private int views = 0;
}
