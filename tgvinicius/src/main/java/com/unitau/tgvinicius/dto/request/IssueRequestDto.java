package com.unitau.tgvinicius.dto.request;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.unitau.tgvinicius.deserializer.IssueDtoDeserializer;
import com.unitau.tgvinicius.enums.IssueState;

@JsonInclude(JsonInclude.Include.NON_NULL)
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