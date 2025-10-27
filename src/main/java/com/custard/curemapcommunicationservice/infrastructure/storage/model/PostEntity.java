package com.custard.curemapcommunicationservice.infrastructure.storage.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "posts")
@Setter
@Getter
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String posterId;
    private String content;
    private boolean isDeleted = false;
    private List<String> mediaUrls;

    @OneToMany(mappedBy = "postEntity", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<CommentEntity> comments;

    private int likes = 0;
    private int dislikes = 0;
    private int flaggedCount = 0;
    private int views = 0;

    @CreationTimestamp
    private Instant createdAt;
    @UpdateTimestamp
    private Instant updatedAt;

    public void addReply(CommentEntity commentEntity) {
        this.comments.add(commentEntity);
        commentEntity.setPostEntity(this);
    }

    public void removeReply(CommentEntity commentEntity) {
        this.comments.remove(commentEntity);
        commentEntity.setPostEntity(null);
    }


}
