package com.unitau.tgvinicius.dto;

import java.time.Instant;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.unitau.tgvinicius.util.CommitDTODeserializer;

@JsonDeserialize(using = CommitDTODeserializer.class)
public record CommitDTO
		(String sha,
		String authorName,
		Instant creation){
}
