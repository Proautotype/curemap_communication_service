package com.custard.curemapcommunicationservice.adapter.dto;

import com.custard.curemapcommunicationservice.domain.model.Comment;
import lombok.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    private String id;
    private String posterId;
    private List<String> mediaUrls;
    private String title;
    private String content;
    private Instant timestamp;
    private boolean isDeleted;
    @Builder.Default
    private List<SimpleCommentDto> comments = new ArrayList<>();

    @Builder.Default
    private int likes = 0;
    @Builder.Default
    private int dislikes = 0;
    @Builder.Default
    private int flaggedCount = 0;
    @Builder.Default
    private int views = 0;

    @Override
    public String toString() {
        return "Post{" +
                "id='" + id + '\'' +
                ", posterId='" + posterId + '\'' +
                ", mediaUrls=" + mediaUrls +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", timestamp=" + timestamp +
                ", isDeleted=" + isDeleted +
                ", comments=" + comments +
                ", likes=" + likes +
                ", dislikes=" + dislikes +
                ", flaggedCount=" + flaggedCount +
                ", views=" + views +
                '}';
    }
}
