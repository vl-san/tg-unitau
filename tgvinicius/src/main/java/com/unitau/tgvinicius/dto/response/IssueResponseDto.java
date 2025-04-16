package com.unitau.tgvinicius.dto.response;

import java.time.Instant;

import com.unitau.tgvinicius.enums.IssueState;

public record IssueResponseDto(
        String id,
        String title,
        IssueState state,
        Instant createdAt,
        Instant updatedAt
) {
}