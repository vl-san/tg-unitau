package com.unitau.tgvinicius.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.unitau.tgvinicius.util.ContributorDTODeserializer;

@JsonDeserialize(using = ContributorDTODeserializer.class)
public record ContributorDTO(String id,
		@JsonProperty("login") String name,
		int contributions,
		String url) {
}