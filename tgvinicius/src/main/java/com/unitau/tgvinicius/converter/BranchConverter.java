package com.unitau.tgvinicius.converter;

import com.unitau.tgvinicius.dto.request.BranchRequestDto;
import com.unitau.tgvinicius.dto.response.BranchResponseDto;
import com.unitau.tgvinicius.entities.Branch;

public class BranchConverter {

	public static Branch dtoToEntity(BranchRequestDto dto) {
		Branch entity = new Branch();
		entity.setName(dto.name());
		
		return entity;
	}
	
    public static BranchResponseDto fromEntity(Branch branch) {
        return new BranchResponseDto(
        		branch.getName());
    }
}