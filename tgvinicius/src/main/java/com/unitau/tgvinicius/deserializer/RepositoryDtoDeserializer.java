package com.unitau.tgvinicius.deserializer;

import java.io.IOException;
import java.time.Instant;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.unitau.tgvinicius.dto.request.RepositoryRequestDto;

public class RepositoryDtoDeserializer extends JsonDeserializer<RepositoryRequestDto> {
	@Override
	public RepositoryRequestDto deserialize(JsonParser parser, DeserializationContext context) throws IOException {
		JsonNode rootNode = parser.getCodec().readTree(parser);

		String id = rootNode.get("id").asText();
		String name = rootNode.get("name").asText();
		String htmlUrl = rootNode.get("html_url").asText();
		Instant created = Instant.parse(rootNode.get("created_at").asText());
		Instant updated = Instant.parse(rootNode.get("updated_at").asText());
		Long size = Long.parseLong(rootNode.get("size").asText());
		Integer stargazers = Integer.parseInt(rootNode.get("stargazers_count").asText());
		Integer watchers = Integer.parseInt(rootNode.get("subscribers_count").asText());
		Integer forks = Integer.parseInt(rootNode.get("forks_count").asText());
		Integer openIssues = Integer.parseInt(rootNode.get("open_issues_count").asText());

		return new RepositoryRequestDto(id, name, htmlUrl, created, updated, size,
				stargazers, watchers, forks, openIssues);
	}
}
