package com.unitau.tgvinicius.util;

import com.unitau.tgvinicius.dto.CommitDTO;
import com.unitau.tgvinicius.entities.Commit;

public class CommitConverter {
	public static Commit dtoToEntity(CommitDTO dto) {
		Commit entity = new Commit();

		entity.setSha(dto.sha());
		entity.setAuthorName(dto.authorName());
		entity.setCreation(dto.creation());

		return entity;
	}

}
