package com.unitau.tgvinicius.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.unitau.tgvinicius.dto.request.ContributorRequestDto;
import com.unitau.tgvinicius.entities.Contributor;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ContributorMapper {
    Contributor toEntity(ContributorRequestDto dto);
}