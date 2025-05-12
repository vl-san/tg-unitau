package com.unitau.tgvinicius.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.unitau.tgvinicius.dto.request.BranchRequestDto;
import com.unitau.tgvinicius.entities.Branch;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BranchMapper {
    Branch toEntity(BranchRequestDto dto);
    }
