package com.unitau.tgvinicius.converter;

import com.unitau.tgvinicius.dto.response.ContributorResponseDto;
import com.unitau.tgvinicius.entities.Contributor;

public class ContributorConverter {

	public static ContributorResponseDto fromEntity(Contributor contributor) {
		return new ContributorResponseDto(
				contributor.getId(),
				contributor.getName(),
				contributor.getUrl());
	}
}