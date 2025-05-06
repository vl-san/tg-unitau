package com.unitau.tgvinicius.deserializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.unitau.tgvinicius.dto.request.BranchRequestDto;

public class BranchDtoDeserializer extends JsonDeserializer<BranchRequestDto> {
    @Override
    public BranchRequestDto deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        JsonNode rootNode = parser.getCodec().readTree(parser);

        String name = rootNode.get("name").asText();
        String commitSha = rootNode.path("commit").path("sha").asText();
        
        return new BranchRequestDto(name, commitSha,  null);
    }
}