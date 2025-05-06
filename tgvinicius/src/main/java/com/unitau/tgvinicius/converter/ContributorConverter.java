package com.unitau.tgvinicius.converter;

import com.unitau.tgvinicius.dto.request.ContributorRequestDto;
import com.unitau.tgvinicius.dto.response.ContributorResponseDto;
import com.unitau.tgvinicius.entities.Commit;
import com.unitau.tgvinicius.entities.Contributor;
import com.unitau.tgvinicius.entities.Issue;

public class ContributorConverter {
	public static Contributor dtoToEntity(ContributorRequestDto dto) {

		Contributor entity = new Contributor();
		entity.setId(dto.id());
		entity.setName(dto.name());
		entity.setUrl(dto.url());

		if (dto.commits() != null) {
			dto.commits().forEach(commitDTO -> {
				Commit commit = CommitConverter.dtoToEntity(commitDTO);
				entity.addCommit(commit);
				commit.setContributor(entity);
			});
		}

		if (dto.issues() != null) {
			dto.issues().forEach(issueDTO -> {
				Issue issue = IssueConverter.dtoToEntity(issueDTO);
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
				contributor.getUrl());
	}
}