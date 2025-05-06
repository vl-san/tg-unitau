package com.unitau.tgvinicius.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.unitau.tgvinicius.converter.IssueConverter;
import com.unitau.tgvinicius.dto.response.IssueResponseDto;
import com.unitau.tgvinicius.entities.Issue;
import com.unitau.tgvinicius.repositories.IssueRepository;
import com.unitau.tgvinicius.services.exceptions.DatabaseException;
import com.unitau.tgvinicius.services.exceptions.ResourceNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class IssueService {

	@Autowired
	private IssueRepository issueRepository;

	public List<IssueResponseDto> findAll() {
	    List<Issue> issues = issueRepository.findAll();
	    return issues.stream()
	                 .map(IssueConverter::fromEntity)
	                 .toList();
	}

	public IssueResponseDto findById(String id) {
	    Issue issue = issueRepository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException(id));
	    return IssueConverter.fromEntity(issue);
	}

	@Transactional
	public Issue insert(Issue obj) {
		try {
			return issueRepository.save(obj);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	@Transactional
	public void delete(String id) {
		try {
			issueRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	@Transactional
	public Issue update(String id, Issue obj) {
		Issue entity = issueRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id));
		updateData(entity, obj);
		return issueRepository.save(entity);
	}

	private void updateData(Issue entity, Issue obj) {
		// entity.setId(obj.getId());
		entity.setTitle(obj.getTitle());
		entity.setState(obj.getState());
		entity.setCreatedAt(obj.getCreatedAt());
		entity.setUpdatedAt(obj.getUpdatedAt());
	}

	@Transactional
	public List<Issue> saveAll(List<Issue> issues) {
		return issueRepository.saveAll(issues);
	}
	
	public List<Issue> findByRepositoryId(String repositoryId) {
        return issueRepository.findByRepositoryId(repositoryId);
    }
}
