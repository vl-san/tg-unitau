package com.unitau.tgvinicius.dto.request;

import java.time.Instant;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.unitau.tgvinicius.deserializer.CommitDtoDeserializer;
import com.unitau.tgvinicius.entities.Contributor;
import com.unitau.tgvinicius.entities.Repository;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(using = CommitDtoDeserializer.class)
public record CommitRequestDto
		(String sha,
		String authorLogin,
		Instant creationAt,
		@JsonInclude(JsonInclude.Include.NON_EMPTY)
		List<BranchRequestDto> branches,
		Contributor contributor,
		Repository repository,
		String contributorId
		){
}
