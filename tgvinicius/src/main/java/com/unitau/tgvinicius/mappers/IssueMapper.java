package com.unitau.tgvinicius.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.unitau.tgvinicius.dto.request.IssueRequestDto;
import com.unitau.tgvinicius.entities.Issue;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IssueMapper {
	Issue toEntity(IssueRequestDto dto);
}