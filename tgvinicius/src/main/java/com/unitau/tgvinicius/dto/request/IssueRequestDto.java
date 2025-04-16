package com.unitau.tgvinicius.dto.request;

import java.time.Instant;

import com.unitau.tgvinicius.enums.IssueState;
import com.unitau.tgvinicius.serialization.IssueDtoDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = IssueDtoDeserializer.class)
public record IssueRequestDto(
        String id,
        String title,
        IssueState state,
        Instant createdAt,
        Instant updatedAt,
        String contributorId
) {
}