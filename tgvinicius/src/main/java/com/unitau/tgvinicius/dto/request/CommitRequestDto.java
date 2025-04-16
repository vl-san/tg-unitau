package com.unitau.tgvinicius.dto.request;

import java.time.Instant;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.unitau.tgvinicius.entities.Contributor;
import com.unitau.tgvinicius.entities.Repository;
import com.unitau.tgvinicius.serialization.CommitDtoDeserializer;

@JsonDeserialize(using = CommitDtoDeserializer.class)
public record CommitRequestDto
		(String sha,
		String authorLogin,
		Instant creationAt,
		List<BranchRequestDto> branches,
		Contributor contributor,
		Repository repository,
		String contributorId
		){
}
