package com.unitau.tgvinicius.converter;

import com.unitau.tgvinicius.dto.request.RepositoryRequestDto;
import com.unitau.tgvinicius.dto.response.RepositoryResponseDto;
import com.unitau.tgvinicius.entities.Repository;
import com.unitau.tgvinicius.entities.RepositoryContributor;

public class RepositoryConverter {

    public static Repository dtoToEntity(RepositoryRequestDto dto) {
        Repository entity = new Repository();

        entity.setId(dto.id());
        entity.setName(dto.name());
        entity.setHtmlUrl(dto.htmlUrl());
        entity.setCreatedAt(dto.createdAt());
        entity.setUpdatedAt(dto.updatedAt());
        entity.setSize(dto.size());
        entity.setStargazers(dto.stargazers());
        entity.setWatchers(dto.watchers());
        entity.setForks(dto.forks());
        entity.setOpenIssues(dto.openIssues());

        if (dto.contributors() != null) {
            dto.contributors().forEach(contributorDTO -> {
                var contributor = ContributorConverter.dtoToEntity(contributorDTO);

                RepositoryContributor repositoryContributor = new RepositoryContributor();
                repositoryContributor.setRepository(entity);
                repositoryContributor.setContributor(contributor);
                repositoryContributor.setContributions(contributorDTO.contributions());

                entity.addRepositoryContributor(repositoryContributor);
            });
        }

        if (dto.commits() != null) {
            dto.commits().forEach(commitDTO -> {
                var commit = CommitConverter.dtoToEntity(commitDTO);
                commit.setRepository(entity);
                entity.addCommit(commit);
            });
        }

        if (dto.issues() != null) {
            dto.issues().forEach(issueDTO -> {
                var issue = IssueConverter.dtoToEntity(issueDTO);
                issue.setRepository(entity);
                entity.addIssue(issue);
            });
        }

        return entity;
    }

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
