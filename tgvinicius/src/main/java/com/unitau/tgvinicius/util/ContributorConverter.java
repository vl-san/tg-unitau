package com.unitau.tgvinicius.util;

import com.unitau.tgvinicius.dto.ContributorDTO;
import com.unitau.tgvinicius.entities.Contributor;

public class ContributorConverter {
	public static Contributor dtoToEntity(ContributorDTO dto) {
		Contributor entity = new Contributor();

		entity.setId(dto.id());
		entity.setName(dto.name());
		entity.setContributions(dto.contributions());
		entity.setUrl(dto.url());

		return entity;
	}
}
