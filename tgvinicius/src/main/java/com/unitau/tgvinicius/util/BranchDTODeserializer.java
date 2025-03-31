package com.unitau.tgvinicius.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.unitau.tgvinicius.dto.BranchDTO;

import java.io.IOException;

public class BranchDTODeserializer extends JsonDeserializer<BranchDTO> {
    @Override
    public BranchDTO deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        JsonNode rootNode = parser.getCodec().readTree(parser);

        String name = rootNode.get("name").asText();
        String shaCommit = rootNode.path("commit").path("sha").asText();

        return new BranchDTO(shaCommit, name);
    }
}