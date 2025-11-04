package com.custard.curemapcommunicationservice.adapter.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Builder
public class SimpleCommentDto {
    private String id;
    private String commentator;
    private String content;
    private Instant lastTimeUpdated;
}
