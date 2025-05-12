package com.unitau.tgvinicius.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.unitau.tgvinicius.dto.request.CommitRequestDto;
import com.unitau.tgvinicius.entities.Commit;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommitMapper {
    Commit toEntity(CommitRequestDto dto);
}