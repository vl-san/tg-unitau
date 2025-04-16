package com.unitau.tgvinicius.serialization;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.unitau.tgvinicius.dto.request.ContributorRequestDto;

public class ContributorDtoDeserializer extends JsonDeserializer<ContributorRequestDto>  {
	@Override
    public ContributorRequestDto deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        JsonNode rootNode = parser.getCodec().readTree(parser);

        String id = rootNode.get("id").asText();
        String name = rootNode.get("login").asText();
        int contributions = Integer.parseInt(rootNode.get("contributions").asText());
        String url = rootNode.get("html_url").asText();

        return new ContributorRequestDto(id, name, contributions, url, List.of(), List.of());
    }
}
