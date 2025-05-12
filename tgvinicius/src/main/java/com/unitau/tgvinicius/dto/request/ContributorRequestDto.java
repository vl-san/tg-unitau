package com.unitau.tgvinicius.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.unitau.tgvinicius.deserializer.ContributorDtoDeserializer;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(using = ContributorDtoDeserializer.class)
public record ContributorRequestDto
		(String id,
		@JsonProperty("login") String name,
		int contributions,
		String url) {
}