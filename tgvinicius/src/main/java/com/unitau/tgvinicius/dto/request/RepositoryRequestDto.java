package com.unitau.tgvinicius.dto.request;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.unitau.tgvinicius.deserializer.RepositoryDtoDeserializer;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(using = RepositoryDtoDeserializer.class)
public record RepositoryRequestDto
		(String id,
		String name,
		String htmlUrl,
		Instant createdAt,
		Instant updatedAt,
		Long size,
		Integer stargazers,
		Integer watchers,
		Integer forks,
		Integer openIssues){
}
