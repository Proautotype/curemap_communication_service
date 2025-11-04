package com.custard.curemapcommunicationservice.domain.ports;

import com.custard.curemapcommunicationservice.application.commands.DeletePostCommand;
import com.custard.curemapcommunicationservice.application.commands.UpdatePostCommand;
import com.custard.curemapcommunicationservice.domain.model.Comment;
import com.custard.curemapcommunicationservice.domain.model.Post;

import java.util.List;

public interface PostRepository {
    Post create(Post post) ;
    Post findPostById(String postId);
    Post findByPosterId(String posterId);
    void updateContent(UpdatePostCommand command);
    void deletePost(DeletePostCommand command);
    Post commentPost( Comment comment);
    void deleteComment(String postId, String commentId);

    void updatePostsComment(String posterId, String commentId, String content);

    List<Comment> findCommentsOnPost(String postId);

    List<Post> searchPostByTitle(String title);
}
