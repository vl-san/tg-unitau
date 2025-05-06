package com.unitau.tgvinicius.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unitau.tgvinicius.converter.RepositoryContributorConverter;
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

    public List<RepositoryContributorResponseDto> findByRepositoryId(String repositoryId) {
        List<RepositoryContributor> list = repositoryContributorRepository.findByIdRepositoryId(repositoryId);
        return list.stream()
                   .map(RepositoryContributorConverter::fromEntity)
                   .collect(Collectors.toList());
    }
}
