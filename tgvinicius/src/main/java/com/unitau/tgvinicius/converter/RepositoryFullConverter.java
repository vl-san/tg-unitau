package com.unitau.tgvinicius.converter;

import com.unitau.tgvinicius.dto.response.RepositoryFullResponseDto;
import com.unitau.tgvinicius.entities.Repository;

public class RepositoryFullConverter {

	public static RepositoryFullResponseDto fromEntity(Repository repository) {
		return new RepositoryFullResponseDto(
				repository.getId(),
				repository.getName(),
				repository.getHtmlUrl(),
				repository.getCreatedAt(),
				repository.getUpdatedAt(),
				repository.getSize(),
				repository.getStargazers(),
				repository.getWatchers(), 
				repository.getForks(),
				repository.getOpenIssues(),
				repository.getRepositoryContributors().stream()
						.map(rc -> ContributorConverter.fromEntity(rc.getContributor())).toList(),
				repository.getCommits().stream()
						.map(CommitConverter::fromEntityWithBranch).toList(),
				repository.getIssues().stream()
						.map(IssueConverter::fromEntity).toList());
	}
}
