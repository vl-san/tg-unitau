package com.unitau.tgvinicius.dto.response;

import java.time.Instant;

public record CommitResponseDto
		(String sha,
		String authorLogin,
		Instant creationAt
		){
}