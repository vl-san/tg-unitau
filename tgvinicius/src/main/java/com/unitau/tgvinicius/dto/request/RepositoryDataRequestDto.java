package com.unitau.tgvinicius.dto.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record RepositoryDataRequestDto(
		RepositoryRequestDto repository,
	    List<ContributorRequestDto> contributors,
	    List<CommitRequestDto> commits,
	    List<BranchRequestDto> branches,
	    List<IssueRequestDto> issues)
{
}
