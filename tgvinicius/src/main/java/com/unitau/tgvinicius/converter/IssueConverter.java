package com.unitau.tgvinicius.converter;

import com.unitau.tgvinicius.dto.response.IssueResponseDto;
import com.unitau.tgvinicius.entities.Issue;

public class IssueConverter {
	
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
