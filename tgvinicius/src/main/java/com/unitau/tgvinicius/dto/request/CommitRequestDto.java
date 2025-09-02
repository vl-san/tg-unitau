package com.unitau.tgvinicius.dto.request;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.unitau.tgvinicius.deserializer.CommitDtoDeserializer;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(using = CommitDtoDeserializer.class)
public record CommitRequestDto
		(String sha,
		Instant createdAt,
		String contributorId
		){
}
