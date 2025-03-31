package com.unitau.tgvinicius.util;

import com.unitau.tgvinicius.dto.BranchDTO;
import com.unitau.tgvinicius.entities.Branch;

public class BranchConverter {

	public static Branch dtoToEntity(BranchDTO dto) {
		Branch entity = new Branch();

		entity.setShaCommit(dto.shaCommit());
		entity.setName(dto.name());
		
		return entity;
	}
}
