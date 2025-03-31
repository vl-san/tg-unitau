package com.unitau.tgvinicius.util;

import java.io.IOException;
import java.time.Instant;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.unitau.tgvinicius.dto.CommitDTO;

public class CommitDTODeserializer extends JsonDeserializer<CommitDTO> {
	@Override
	public CommitDTO deserialize(JsonParser parser, DeserializationContext context) throws IOException {
		JsonNode rootNode = parser.getCodec().readTree(parser);

		String sha = rootNode.get("sha").asText();
		String authorName = rootNode.path("commit").path("author").path("name").asText();
		Instant creation = Instant.parse(rootNode.path("commit").path("author").path("date").asText());

		return new CommitDTO(sha, authorName, creation);
	}
}