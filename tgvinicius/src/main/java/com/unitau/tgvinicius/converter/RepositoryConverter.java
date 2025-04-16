package com.unitau.tgvinicius.converter;

import java.util.List;

import com.unitau.tgvinicius.dto.request.RepositoryRequestDto;
import com.unitau.tgvinicius.dto.response.RepositoryResponseDto;
import com.unitau.tgvinicius.entities.Branch;
import com.unitau.tgvinicius.entities.Commit;
import com.unitau.tgvinicius.entities.Contributor;
import com.unitau.tgvinicius.entities.Issue;
import com.unitau.tgvinicius.entities.Repository;

public class RepositoryConverter {

	public static Repository dtoToEntity(RepositoryRequestDto dto) {
		Repository entity = new Repository();

		entity.setId(dto.id());
		entity.setName(dto.name());
		entity.setHtmlUrl(dto.htmlUrl());
		entity.setCreatedAt(dto.createdAt());
		entity.setUpdatedAt(dto.updatedAt());
		entity.setSize(dto.size());
		entity.setStargazers(dto.stargazers());
		entity.setWatchers(dto.watchers());
		entity.setForks(dto.forks());
		entity.setOpenIssues(dto.openIssues());

		if (dto.contributors() != null) {
			dto.contributors().forEach(contributorDTO -> {
				Contributor contributor = new Contributor();

				contributor.setId(contributorDTO.id());
				contributor.setName(contributorDTO.name());
				contributor.setContributions(contributorDTO.contributions());
				contributor.setUrl(contributorDTO.url());

				if (dto.commits() != null) {
					dto.commits().forEach(commitDTO -> {
						Commit commit = new Commit();

						commit.setSha(commitDTO.sha());
						commit.setAuthorLogin(commitDTO.authorLogin());
						commit.setCreationAt(commitDTO.creationAt());

						if (commitDTO.branches() != null) {
							commitDTO.branches().forEach(branchDTO -> {
								Branch branch = new Branch();
								branch.setName(branchDTO.name());
								commit.addBranch(branch);
								branch.setCommit(commit);
							});
						}

						entity.addCommit(commit);
						commit.setRepository(entity);

					});
				}

				if (dto.issues() != null) {
					dto.issues().forEach(issueDTO -> {
						Issue issue = new Issue();

						issue.setId(issueDTO.id());
						issue.setTitle(issueDTO.title());
						issue.setCreatedAt(issueDTO.createdAt());
						issue.setUpdatedAt(issueDTO.updatedAt());

						entity.addIssue(issue);
						issue.setRepository(entity);
					});
				}

				entity.addContributor(contributor);
				contributor.setRepository(entity);

			});
		}

		return entity;
	}

	public static RepositoryResponseDto fromEntity(Repository repository) {
	    return new RepositoryResponseDto(
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
	        repository.getContributors().stream()
	            .map(ContributorConverter::fromEntity)
	            .toList(),
	            List.of(),
	        repository.getIssues().stream()
	            .map(IssueConverter::fromEntity)
	            .toList()
	    );
	}

}
