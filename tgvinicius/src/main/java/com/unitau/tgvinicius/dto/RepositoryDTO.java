package com.unitau.tgvinicius.dto;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RepositoryDTO
		(String id,
		String name,
		@JsonProperty("html_url") String htmlUrl,
		@JsonProperty("created_at") Instant created,
		@JsonProperty("updated_at") Instant updated,
		Long size,
		@JsonProperty("stargazers_count") Integer stargazers,
		@JsonProperty("watchers_count") Integer watchers,
		String language,
		@JsonProperty("forks_count") Integer forks,
		@JsonProperty("open_issues_count") Integer openIssues){
}
