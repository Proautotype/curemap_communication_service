package com.custard.curemapcommunicationservice.infrastructure.storage.data;

import com.custard.curemapcommunicationservice.infrastructure.storage.model.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface JpaCommentRepository extends JpaRepository<CommentEntity, String> {

    List<CommentEntity> findByPostEntityId(String postId);

    @Query("SELECT c FROM CommentEntity c WHERE c.id = :commentId AND c.postEntity.id = :postId")
    Optional<CommentEntity> findByIdAndPostId(@Param("commentId") String commentId,
                                           @Param("postId") String postId);

    @Modifying
    @Transactional
    @Query("UPDATE CommentEntity c SET c.content = :content WHERE c.id = :commentId AND c.postEntity.id = :postId")
    int updateCommentContent(@Param("postId") String postId,
                           @Param("commentId") String commentId,
                           @Param("content") String content);

    @Modifying
    @Transactional
    @Query("DELETE FROM CommentEntity c WHERE c.id = :commentId AND c.postEntity.id = :postId")
    int deleteComment(@Param("postId") String postId,
                     @Param("commentId") String commentId);

    boolean existsByIdAndPostEntityId(String commentId, String postId);
}
