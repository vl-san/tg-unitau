package com.unitau.tgvinicius.converter;

import com.unitau.tgvinicius.dto.response.RepositoryContributorResponseDto;
import com.unitau.tgvinicius.entities.RepositoryContributor;

public class RepositoryContributorConverter {
    public static RepositoryContributorResponseDto fromEntity(RepositoryContributor repositoryContributor) {
        return new RepositoryContributorResponseDto(
            repositoryContributor.getRepository().getId(),
            repositoryContributor.getRepository().getName(),
            repositoryContributor.getContributor().getId(),
            repositoryContributor.getContributor().getName(),
            repositoryContributor.getContributions()
        );
    }
}
