package com.unitau.tgvinicius.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unitau.tgvinicius.entities.Issue;
import com.unitau.tgvinicius.repositories.IssueRepository;

@Service
public class IssueService {

	@Autowired
	private IssueRepository issueRepository;

	public List<Issue> findAll() {
		return issueRepository.findAll();
	}

	public Issue findById(String id) {
		Optional<Issue> obj = issueRepository.findById(id);
		return obj.get();
	}
}
