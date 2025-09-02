package com.unitau.tgvinicius.dto.response;

public record RepositoryContributorResponseDto(
    String repositoryId,
    String repositoryName,
    String contributorId,
    String contributorName,
    Integer contributions,
    Integer commitsCount,
    Integer issuesCount,
    Double percentCommits,
    Double percentIssues
) {}
