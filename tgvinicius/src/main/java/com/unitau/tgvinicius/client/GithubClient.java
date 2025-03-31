package com.unitau.tgvinicius.client;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.service.annotation.GetExchange;

import com.unitau.tgvinicius.dto.BranchDTO;
import com.unitau.tgvinicius.dto.CommitDTO;
import com.unitau.tgvinicius.dto.ContributorDTO;
import com.unitau.tgvinicius.dto.IssueDTO;
import com.unitau.tgvinicius.dto.RepositoryDTO;

public interface GithubClient {

	@GetExchange("users/{username}/repos")
	public List<RepositoryDTO> listRepos(
			@RequestHeader("Authorization") String token,
			@RequestHeader(value = "X-GitHub-Api-Version", defaultValue = "2022-11-28") String apiVersion,
			@PathVariable("username") String username);
	
	@GetExchange("repos/{username}/{repository}/branches")
	public List<BranchDTO> listBranches(
	        @RequestHeader("Authorization") String token,
	        @RequestHeader(value = "X-GitHub-Api-Version", defaultValue = "2022-11-28") String apiVersion,
	        @PathVariable("username") String username,
	        @PathVariable("repository") String repository // Adicionar o repositório
	);
	
	@GetExchange("repos/{username}/{repository}/issues")
	public List<IssueDTO> listIssues(
	        @RequestHeader("Authorization") String token,
	        @RequestHeader(value = "X-GitHub-Api-Version", defaultValue = "2022-11-28") String apiVersion,
	        @PathVariable("username") String username,
	        @PathVariable("repository") String repository // Adicionar o repositório
	);
	
	@GetExchange("repos/{username}/{repository}/contributors")
	public List<ContributorDTO> listContributors(
	        @RequestHeader("Authorization") String token,
	        @RequestHeader(value = "X-GitHub-Api-Version", defaultValue = "2022-11-28") String apiVersion,
	        @PathVariable("username") String username,
	        @PathVariable("repository") String repository // Adicionar o repositório
	);
	
	@GetExchange("repos/{username}/{repository}/commits")
	public List<CommitDTO> listCommits(
	        @RequestHeader("Authorization") String token,
	        @RequestHeader(value = "X-GitHub-Api-Version", defaultValue = "2022-11-28") String apiVersion,
	        @PathVariable("username") String username,
	        @PathVariable("repository") String repository // Adicionar o repositório
	);
}