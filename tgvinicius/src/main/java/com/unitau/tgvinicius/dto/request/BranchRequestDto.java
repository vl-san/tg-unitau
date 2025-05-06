package com.unitau.tgvinicius.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.unitau.tgvinicius.deserializer.BranchDtoDeserializer;
import com.unitau.tgvinicius.entities.Commit;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(using = BranchDtoDeserializer.class)
public record BranchRequestDto(
        String name,
        String commitSha,
        Commit commit){
}