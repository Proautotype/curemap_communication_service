package com.custard.curemapcommunicationservice.infrastructure.storage.data;

import com.custard.curemapcommunicationservice.domain.model.Post;
import com.custard.curemapcommunicationservice.infrastructure.storage.model.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaPostRepository extends JpaRepository<PostEntity, String>, JpaSpecificationExecutor<Post> {
    List<PostEntity> findByPosterId(String posterId);
}
