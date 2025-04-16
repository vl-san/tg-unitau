package com.unitau.tgvinicius.converter;

import com.unitau.tgvinicius.dto.request.ContributorRequestDto;
import com.unitau.tgvinicius.dto.response.ContributorResponseDto;
import com.unitau.tgvinicius.dto.response.IssueResponseDto;
import com.unitau.tgvinicius.entities.Branch;
import com.unitau.tgvinicius.entities.Commit;
import com.unitau.tgvinicius.entities.Contributor;
import com.unitau.tgvinicius.entities.Issue;

public class ContributorConverter {
	public static Contributor dtoToEntity(ContributorRequestDto dto) {
		Contributor entity = new Contributor();

		entity.setId(dto.id());
		entity.setName(dto.name());
		entity.setContributions(dto.contributions());
		entity.setUrl(dto.url());

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
				commit.setContributor(entity);
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
				issue.setContributor(entity);
				
			});
		}

		return entity;
	}
	
	public static ContributorResponseDto fromEntity(Contributor contributor) {
	    return new ContributorResponseDto(
	        contributor.getId(),
	        contributor.getName(),
	        contributor.getContributions(),
	        contributor.getUrl(),
	        contributor.getCommits().stream()
            .map(CommitConverter::fromEntity)
            .toList(),
	        contributor.getIssues().stream()
	            .map(issue -> new IssueResponseDto(
	                issue.getId(),
	                issue.getTitle(),
	                issue.getState(),
	                issue.getCreatedAt(),
	                issue.getUpdatedAt()
	            ))
	            .toList()
	    );
	}


}