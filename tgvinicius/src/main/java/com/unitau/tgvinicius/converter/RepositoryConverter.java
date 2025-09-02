package com.unitau.tgvinicius.converter;

import java.time.Duration;

import com.unitau.tgvinicius.dto.response.RepositoryResponseDto;
import com.unitau.tgvinicius.entities.Repository;

public class RepositoryConverter {

    public static RepositoryResponseDto fromEntity(Repository repository) {
        Duration duration = repository.getInactivityDuration();
        String formattedDuration = (duration != null)
                ? String.format("%d dias, %d horas", duration.toDays(), duration.toHoursPart())
                : "0 dias, 0 horas";
        
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
            repository.getOpenIssues(),
            repository.getTotalIssues(),
            repository.getTotalCommits(),
            repository.getTotalContributors(),
            repository.getAvgIssuesPerContributor(),
            repository.getAvgPercentIssuesPerContributor(),
            repository.getAvgCommitsPerContributor(),
            repository.getAvgPercentCommitsPerContributor(),
            formattedDuration
        );
    }
}
