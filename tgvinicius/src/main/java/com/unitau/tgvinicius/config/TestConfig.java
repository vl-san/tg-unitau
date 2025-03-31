package com.unitau.tgvinicius.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.unitau.tgvinicius.repositories.BranchRepository;
import com.unitau.tgvinicius.repositories.CommitRepository;
import com.unitau.tgvinicius.repositories.IssueRepository;
import com.unitau.tgvinicius.repositories.RepositoryRepository;
import com.unitau.tgvinicius.repositories.ContributorRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private ContributorRepository userRepository;
	@Autowired
	private RepositoryRepository repositoryRepository;
	@Autowired
	private BranchRepository branchRepository;
	@Autowired
	private CommitRepository commitRepository;
	@Autowired
	private IssueRepository issueRepository;

	@Override
	public void run(String... args) throws Exception {
		
		repositoryRepository.deleteAll();
	    userRepository.deleteAll();
		branchRepository.deleteAll();
		commitRepository.deleteAll();
		issueRepository.deleteAll();
	}
}