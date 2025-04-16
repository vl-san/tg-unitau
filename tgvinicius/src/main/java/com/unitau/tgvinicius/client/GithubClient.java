package com.unitau.tgvinicius.client;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

import com.unitau.tgvinicius.dto.request.BranchRequestDto;
import com.unitau.tgvinicius.dto.request.CommitRequestDto;
import com.unitau.tgvinicius.dto.request.ContributorRequestDto;
import com.unitau.tgvinicius.dto.request.IssueRequestDto;
import com.unitau.tgvinicius.dto.request.RepositoryRequestDto;

public interface GithubClient {
	
	@GetExchange("repos/{username}/{repository}")
	public RepositoryRequestDto listRepository(
			@RequestHeader("Authorization") String token,
			@PathVariable("username") String username,
			@PathVariable("repository") String repository
	);
	
	@GetExchange("repos/{username}/{repository}/branches")
	public List<BranchRequestDto> listBranches(
	        @RequestHeader("Authorization") String token,
	        @PathVariable("username") String username,
	        @PathVariable("repository") String repository,
	        @RequestParam(value = "page", defaultValue = "1") int page,
	        @RequestParam(value = "per_page", defaultValue = "30") int perPage
	);
	
	@GetExchange("repos/{username}/{repository}/issues")
	public List<IssueRequestDto> listIssues(
	        @RequestHeader("Authorization") String token,
	        @PathVariable("username") String username,
	        @PathVariable("repository") String repository,
	        @RequestParam(value = "page", defaultValue = "1") int page,
	        @RequestParam(value = "per_page", defaultValue = "30") int perPage
	);
	
	@GetExchange("repos/{username}/{repository}/contributors")
	public List<ContributorRequestDto> listContributors(
	        @RequestHeader("Authorization") String token,
	        @PathVariable("username") String username,
	        @PathVariable("repository") String repository,
	        @RequestParam(value = "page", defaultValue = "1") int page,
	        @RequestParam(value = "per_page", defaultValue = "30") int perPage
	);
	
	@GetExchange("repos/{username}/{repository}/commits")
	public List<CommitRequestDto> listCommits(
	        @RequestHeader("Authorization") String token,
	        @PathVariable("username") String username,
	        @PathVariable("repository") String repository,
	        @RequestParam(value = "page", defaultValue = "1") int page,
	        @RequestParam(value = "per_page", defaultValue = "30") int perPage
	);
}