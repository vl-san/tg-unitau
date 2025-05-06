package com.unitau.tgvinicius.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.unitau.tgvinicius.converter.ContributorConverter;
import com.unitau.tgvinicius.dto.response.ContributorResponseDto;
import com.unitau.tgvinicius.entities.Contributor;
import com.unitau.tgvinicius.repositories.ContributorRepository;
import com.unitau.tgvinicius.services.exceptions.DatabaseException;
import com.unitau.tgvinicius.services.exceptions.ResourceNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class ContributorService {

	@Autowired
	private ContributorRepository contributorRepository;

	public List<ContributorResponseDto> findAll() {
	    List<Contributor> contributors = contributorRepository.findAll();
	    return contributors.stream()
	                       .map(ContributorConverter::fromEntity)
	                       .toList();
	}
	
	public ContributorResponseDto findById(String id) {
	    Contributor contributor = contributorRepository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException(id));
	    return ContributorConverter.fromEntity(contributor);
	}

	@Transactional
	public Contributor insert(Contributor obj) {
		try {
			return contributorRepository.save(obj);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	@Transactional
	public void delete(String id) {
		try {
			contributorRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	@Transactional
	public Contributor update(String id, Contributor obj) {
		Contributor entity = contributorRepository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException(id));
		updateData(entity, obj);
		return contributorRepository.save(entity);
	}

	private void updateData(Contributor entity, Contributor obj) {
//		entity.setId(obj.getId());
		entity.setName(obj.getName());
		entity.setUrl(obj.getUrl());
	}

	@Transactional
	public List<Contributor> saveAll(List<Contributor> contributors) {
		return contributorRepository.saveAll(contributors);
	}
	
	public List<Contributor> findByRepositoryId(String repositoryId) {
        return contributorRepository.findByRepositoryId(repositoryId);
    }
}
