package com.unitau.tgvinicius.dto.response;

import java.time.Instant;
import java.util.List;

public record CommitResponseDto
		(String sha,
		String authorLogin,
		Instant creationAt,
		List<BranchResponseDto> branches
		){
}