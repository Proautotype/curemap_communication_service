package com.custard.curemapcommunicationservice.domain.ports;

import com.custard.curemapcommunicationservice.domain.model.Comment;
import com.custard.curemapcommunicationservice.domain.model.Post;

import java.util.List;

public interface PostCommentRepository {
    void create(Post post);
    List<Post> findByPosterId(String posterId);
    void updateContent(String postId, String content);
    void deletePost(String postId);
    Post commentPost( Comment comment);
    void deleteComment(String postId, String commentId);

    void updatePostsComment(String posterId, String commentId, String content);

    List<Comment> findCommentsOnPost(String postId);
}
