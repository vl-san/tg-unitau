package com.unitau.tgvinicius.converter;


import com.unitau.tgvinicius.dto.response.BranchResponseDto;
import com.unitau.tgvinicius.entities.Branch;

public class BranchConverter {
	
    public static BranchResponseDto fromEntity(Branch branch) {
        return new BranchResponseDto(
        		branch.getName());
    }
}