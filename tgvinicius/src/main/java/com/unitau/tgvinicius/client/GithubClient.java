package com.unitau.tgvinicius.client;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.service.annotation.GetExchange;

import com.unitau.tgvinicius.dto.RepositoryDTO;

public interface GithubClient {

	@GetExchange("users/{username}/repos")
	public List<RepositoryDTO> listRepos(
			@RequestHeader("Authorization") String token,
			@RequestHeader(value = "X-GitHub-Api-Version", defaultValue = "2022-11-28") String apiVersion,
			@PathVariable("username") String username);
}
