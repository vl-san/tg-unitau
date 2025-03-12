package com.unitau.tgvinicius.config;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.unitau.tgvinicius.entities.Branch;
import com.unitau.tgvinicius.entities.Commit;
import com.unitau.tgvinicius.entities.Issue;
import com.unitau.tgvinicius.entities.Repository;
import com.unitau.tgvinicius.entities.User;
import com.unitau.tgvinicius.enums.IssueState;
import com.unitau.tgvinicius.repositories.BranchRepository;
import com.unitau.tgvinicius.repositories.CommitRepository;
import com.unitau.tgvinicius.repositories.IssueRepository;
import com.unitau.tgvinicius.repositories.RepositoryRepository;
import com.unitau.tgvinicius.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
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