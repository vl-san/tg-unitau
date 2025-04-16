package com.unitau.tgvinicius.dto.request;

import java.time.Instant;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.unitau.tgvinicius.serialization.RepositoryDtoDeserializer;

@JsonDeserialize(using = RepositoryDtoDeserializer.class)
public record RepositoryRequestDto
		(String id,
		String name,
		String htmlUrl,
		Instant createdAt,
		Instant updatedAt,
		Long size,
		Integer stargazers,
		Integer watchers,
		Integer forks,
		Integer openIssues,
		List<ContributorRequestDto> contributors,
		List<CommitRequestDto> commits,
		List<IssueRequestDto> issues){
}
