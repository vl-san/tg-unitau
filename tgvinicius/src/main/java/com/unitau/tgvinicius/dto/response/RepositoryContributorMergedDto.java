package com.unitau.tgvinicius.dto.response;

public record RepositoryContributorMergedDto(
	    String repositoryId,
	    String repositoryName,
	    String contributorId,
	    String contributorName,
	    String contributorUrl,
	    Integer contributions,
	    Integer commitsCount,
	    Integer issuesCount,
	    Double percentCommits,
	    Double percentIssues
	) {}
