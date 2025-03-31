package com.unitau.tgvinicius.util;

import com.unitau.tgvinicius.dto.IssueDTO;
import com.unitau.tgvinicius.entities.Issue;

public class IssueConverter {

	public static Issue dtoToEntity(IssueDTO dto) {
		Issue entity = new Issue();

		entity.setId(dto.id());
		entity.setTitle(dto.title());
		entity.setState(dto.state());
		entity.setCreatedAt(dto.createdAt());
		entity.setUpdatedAt(dto.updatedAt());

		return entity;
	}
}
