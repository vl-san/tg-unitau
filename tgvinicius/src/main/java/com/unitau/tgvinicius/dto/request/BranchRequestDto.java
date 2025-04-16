package com.unitau.tgvinicius.dto.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.unitau.tgvinicius.entities.Commit;
import com.unitau.tgvinicius.serialization.BranchDtoDeserializer;

@JsonDeserialize(using = BranchDtoDeserializer.class)
public record BranchRequestDto(
        String name,
        Commit commit,
        String commitSha){
}