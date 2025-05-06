package com.unitau.tgvinicius.dto.response;

import java.time.Instant;
import java.util.List;

public record RepositoryFullResponseDto(
	    String id,
	    String name,
	    String htmlUrl,
	    Instant createdAt,
	    Instant updatedAt,
	    Long size,
	    Integer stargazers,
	    Integer watchers,
	    Integer forks,
	    Integer openIssues,
	    List<ContributorResponseDto> contributors,
	    List<CommitFullResponseDto> commits,
	    List<IssueResponseDto> issues
	) {}
