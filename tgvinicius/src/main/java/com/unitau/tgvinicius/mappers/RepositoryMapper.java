package com.unitau.tgvinicius.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.unitau.tgvinicius.dto.request.RepositoryRequestDto;
import com.unitau.tgvinicius.entities.Repository;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RepositoryMapper {
    Repository toEntity(RepositoryRequestDto dto);
}

