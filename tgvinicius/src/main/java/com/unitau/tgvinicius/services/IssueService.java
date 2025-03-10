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
	
	public Issue insert(Issue obj) {
		return issueRepository.save(obj);
	}

	public void delete(String id) {
		issueRepository.deleteById(id);
	}

	public Issue update(String id, Issue obj) {
		Issue entity = issueRepository.getReferenceById(id);
		updateData(entity, obj);
		return issueRepository.save(entity);
	}

	private void updateData(Issue entity, Issue obj) {
		// entity.setId(obj.getId());
		entity.setName(obj.getName());
		entity.setState(obj.getState());
		entity.setCreation(obj.getCreation());
	}
}
