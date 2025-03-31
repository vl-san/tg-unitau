package com.unitau.tgvinicius.dto;

import java.time.Instant;

import com.unitau.tgvinicius.enums.IssueState;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.unitau.tgvinicius.util.IssueDTODeserializer;

@JsonDeserialize(using = IssueDTODeserializer.class)
public record IssueDTO(
        String id,
        String title,
        IssueState state,
        Instant createdAt,
        Instant updatedAt
) {
}