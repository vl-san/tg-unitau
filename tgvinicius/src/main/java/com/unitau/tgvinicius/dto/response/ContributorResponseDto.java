package com.unitau.tgvinicius.dto.response;

import java.util.List;

public record ContributorResponseDto
		(String id,
		String name,
		int contributions,
		String url,
		List<CommitResponseDto> commits,
		List<IssueResponseDto> issues
		) {
}