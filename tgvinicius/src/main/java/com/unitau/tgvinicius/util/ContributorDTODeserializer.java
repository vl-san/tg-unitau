package com.unitau.tgvinicius.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.unitau.tgvinicius.dto.ContributorDTO;

public class ContributorDTODeserializer extends JsonDeserializer<ContributorDTO>  {
	@Override
    public ContributorDTO deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        JsonNode rootNode = parser.getCodec().readTree(parser);

        String id = rootNode.get("id").asText();
        String name = rootNode.get("login").asText();
        int contributions = Integer.parseInt(rootNode.get("contributions").asText());
        String url = rootNode.get("html_url").asText();

        return new ContributorDTO(id, name, contributions, url);
    }
}
