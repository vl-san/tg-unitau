package com.unitau.tgvinicius.util;

import com.unitau.tgvinicius.dto.RepositoryDTO;
import com.unitau.tgvinicius.entities.Repository;

public class RepositoryConverter {

	public static Repository dtoToEntity(RepositoryDTO dto) {
		Repository entity = new Repository();

		entity.setId(dto.id());
		entity.setName(dto.name());
		entity.setHtmlUrl(dto.htmlUrl());
		entity.setCreated(dto.created());
		entity.setUpdated(dto.updated());
		entity.setSize(dto.size());
		entity.setStargazers(dto.stargazers());
		entity.setWatchers(dto.watchers());
		entity.setForks(dto.forks());
		entity.setOpenIssues(dto.openIssues());
		
		return entity;
	}
}
