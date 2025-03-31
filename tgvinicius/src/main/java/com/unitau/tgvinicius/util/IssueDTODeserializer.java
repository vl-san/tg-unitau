package com.unitau.tgvinicius.util;

import java.io.IOException;
import java.time.Instant;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.unitau.tgvinicius.dto.IssueDTO;
import com.unitau.tgvinicius.enums.IssueState;

public class IssueDTODeserializer extends JsonDeserializer<IssueDTO> {
	@Override
    public IssueDTO deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        JsonNode rootNode = parser.getCodec().readTree(parser);

        String id = rootNode.get("id").asText();
        String title = rootNode.get("title").asText();

        String stateText = rootNode.get("state").asText();
        IssueState state = IssueState.valueOf(stateText.toUpperCase());

        Instant createdAt = Instant.parse(rootNode.get("created_at").asText());
        Instant updatedAt= Instant.parse(rootNode.get("updated_at").asText());

        return new IssueDTO(id, title, state, createdAt, updatedAt);
    }
}
