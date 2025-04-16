package com.unitau.tgvinicius.converter;

import com.unitau.tgvinicius.dto.request.IssueRequestDto;
import com.unitau.tgvinicius.dto.response.IssueResponseDto;
import com.unitau.tgvinicius.entities.Issue;

public class IssueConverter {

	public static Issue dtoToEntity(IssueRequestDto dto) {
		Issue entity = new Issue();

		entity.setId(dto.id());
		entity.setTitle(dto.title());
		entity.setState(dto.state());
		entity.setCreatedAt(dto.createdAt());
		entity.setUpdatedAt(dto.updatedAt());

		return entity;
	}
	
	public static IssueResponseDto fromEntity(Issue issue) {
	    return new IssueResponseDto(
	        issue.getId(),
	        issue.getTitle(),
	        issue.getState(),
	        issue.getCreatedAt(),
	        issue.getUpdatedAt()
	    );
	}
}
