package com.custard.curemapcommunicationservice.infrastructure.storage.data;

import com.custard.curemapcommunicationservice.domain.model.Post;
import com.custard.curemapcommunicationservice.infrastructure.storage.model.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaPostRepository extends JpaRepository<PostEntity, String>, JpaSpecificationExecutor<Post> {
    List<PostEntity> findByPosterId(String posterId);

    @Query("select p from PostEntity p where p.id = ?1 and p.posterId = ?2")
    Optional<PostEntity> findByIdAndPosterId(String id, String posterId);
}
