package com.unitau.tgvinicius.dto.request;

import java.util.List;

public record RepositoryDataRequestDto(
		RepositoryRequestDto repository,
	    List<ContributorRequestDto> contributors,
	    List<CommitRequestDto> commits,
	    List<BranchRequestDto> branches,
	    List<IssueRequestDto> issues)
{
}
