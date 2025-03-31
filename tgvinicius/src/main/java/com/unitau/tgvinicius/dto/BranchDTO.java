package com.unitau.tgvinicius.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.unitau.tgvinicius.util.BranchDTODeserializer;

@JsonDeserialize(using = BranchDTODeserializer.class)
public record BranchDTO(
        String shaCommit,
        String name
) {
}