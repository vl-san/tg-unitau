package com.unitau.tgvinicius.deserializer;

import java.io.IOException;
import java.time.Instant;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.unitau.tgvinicius.dto.request.CommitRequestDto;

public class CommitDtoDeserializer extends JsonDeserializer<CommitRequestDto> {
	@Override
	public CommitRequestDto deserialize(JsonParser parser, DeserializationContext context) throws IOException {
		JsonNode rootNode = parser.getCodec().readTree(parser);

		String sha = rootNode.get("sha").asText();
		Instant creation = Instant.parse(rootNode.path("commit").path("author").path("date").asText());
		String contributorId = rootNode.path("author").path("id").asText();
		
		return new CommitRequestDto(sha, creation, contributorId);
	}
}