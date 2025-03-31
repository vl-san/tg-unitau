package com.unitau.tgvinicius.dto;

import java.time.Instant;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.unitau.tgvinicius.util.RepositoryDTODeserializer;

@JsonDeserialize(using = RepositoryDTODeserializer.class)
public record RepositoryDTO
		(String id,
		String name,
		String htmlUrl,
		Instant created,
		Instant updated,
		Long size,
		Integer stargazers,
		Integer watchers,
		Integer forks,
		Integer openIssues){
}
