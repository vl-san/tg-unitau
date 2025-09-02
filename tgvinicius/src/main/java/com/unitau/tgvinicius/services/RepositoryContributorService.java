package com.unitau.tgvinicius.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unitau.tgvinicius.converter.RepositoryContributorConverter;
import com.unitau.tgvinicius.dto.response.RepositoryContributorMergedDto;
import com.unitau.tgvinicius.dto.response.RepositoryContributorResponseDto;
import com.unitau.tgvinicius.entities.RepositoryContributor;
import com.unitau.tgvinicius.repositories.RepositoryContributorRepository;

@Service
public class RepositoryContributorService {

    @Autowired
    private RepositoryContributorRepository repositoryContributorRepository;
    
    public List<RepositoryContributorResponseDto> findAll() {
        List<RepositoryContributor> list = repositoryContributorRepository.findAll();
        return list.stream()
                   .map(RepositoryContributorConverter::fromEntity)
                   .collect(Collectors.toList());
    }

    public List<RepositoryContributorResponseDto> findByContributorId(String contributorId) {
        List<RepositoryContributor> list = repositoryContributorRepository.findByIdContributorId(contributorId);
        return list.stream()
                   .map(RepositoryContributorConverter::fromEntity)
                   .collect(Collectors.toList());
    }

//    public List<RepositoryContributorResponseDto> findByRepositoryId(String repositoryId) {
//        List<RepositoryContributor> list = repositoryContributorRepository.findByIdRepositoryId(repositoryId);
//        return list.stream()
//                   .map(RepositoryContributorConverter::fromEntity)
//                   .collect(Collectors.toList());
//    }
    
    public List<RepositoryContributorMergedDto> findByRepositoryId(String repositoryId) {
        List<RepositoryContributor> repoContributors = repositoryContributorRepository.findByIdRepositoryId(repositoryId);
        return repoContributors.stream()
            .map(rc -> new RepositoryContributorMergedDto(
                rc.getRepository().getId(),
                rc.getRepository().getName(),
                rc.getContributor().getId(),
                rc.getContributor().getName(),
                rc.getContributor().getUrl(),
                rc.getContributions(),
                rc.getCommitsCount(),
                rc.getIssuesCount(),
                rc.getPercentCommits(),
                rc.getPercentIssues()
            ))
            .toList();
    }


}
