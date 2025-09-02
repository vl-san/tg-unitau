package com.unitau.tgvinicius.dto.response;

import java.time.Instant;

public record RepositoryResponseDto
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
		Integer totalIssues,
		Integer totalCommits,
		Integer totalContributors,
		Double avgIssuesPerContributor,
		Double avgPercentIssuesPerContributor,
		Double avgCommitsPerContributor,
		Double avgPercentCommitsPerContributor,
		String inactivityDuration) {
}
