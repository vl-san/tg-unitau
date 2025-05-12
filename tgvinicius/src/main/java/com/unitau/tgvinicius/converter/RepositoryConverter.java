package com.unitau.tgvinicius.converter;

import com.unitau.tgvinicius.dto.response.RepositoryResponseDto;
import com.unitau.tgvinicius.entities.Repository;

public class RepositoryConverter {

    public static RepositoryResponseDto fromEntity(Repository repository) {
        return new RepositoryResponseDto(
            repository.getId(),
            repository.getName(),
            repository.getHtmlUrl(),
            repository.getCreatedAt(),
            repository.getUpdatedAt(),
            repository.getSize(),
            repository.getStargazers(),
            repository.getWatchers(),
            repository.getForks(),
            repository.getOpenIssues()
        );
    }
}
