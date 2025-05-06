package com.unitau.tgvinicius.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unitau.tgvinicius.dto.response.RepositoryContributorResponseDto;
import com.unitau.tgvinicius.services.RepositoryContributorService;

@RestController
@RequestMapping("/repository-contributors")
public class RepositoryContributorResource {

    @Autowired
    private RepositoryContributorService repositoryContributorService;

    @GetMapping
    public ResponseEntity<List<RepositoryContributorResponseDto>> findAll() {
        List<RepositoryContributorResponseDto> list = repositoryContributorService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/repository/{repositoryId}")
    public ResponseEntity<List<RepositoryContributorResponseDto>> findByRepositoryId(@PathVariable String repositoryId) {
        List<RepositoryContributorResponseDto> contributors = repositoryContributorService.findByRepositoryId(repositoryId);
        return ResponseEntity.ok(contributors);
    }
    
    @GetMapping("/contributor/{contributorId}")
    public ResponseEntity<List<RepositoryContributorResponseDto>> findByContributorId(@PathVariable String contributorId) {
        List<RepositoryContributorResponseDto> list = repositoryContributorService.findByContributorId(contributorId);
        return ResponseEntity.ok(list);
    }
}
