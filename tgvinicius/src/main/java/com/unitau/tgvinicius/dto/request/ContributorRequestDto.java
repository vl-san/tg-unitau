package com.unitau.tgvinicius.dto.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.unitau.tgvinicius.serialization.ContributorDtoDeserializer;

@JsonDeserialize(using = ContributorDtoDeserializer.class)
public record ContributorRequestDto(String id,
		@JsonProperty("login") String name,
		int contributions,
		String url,
		List<CommitRequestDto> commits,
		List<IssueRequestDto> issues
		) {
}