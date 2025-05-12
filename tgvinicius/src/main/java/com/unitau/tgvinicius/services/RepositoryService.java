package com.unitau.tgvinicius.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.unitau.tgvinicius.converter.RepositoryConverter;
import com.unitau.tgvinicius.dto.request.RepositoryRequestDto;
import com.unitau.tgvinicius.dto.response.RepositoryResponseDto;
import com.unitau.tgvinicius.entities.Repository;
import com.unitau.tgvinicius.repositories.RepositoryRepository;
import com.unitau.tgvinicius.services.exceptions.DatabaseException;
import com.unitau.tgvinicius.services.exceptions.ResourceNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class RepositoryService {

	@Autowired
	private RepositoryRepository repositoryRepository;

	public List<RepositoryResponseDto> findAll() {
	    List<Repository> entities = repositoryRepository.findAll();
	    return entities.stream()
	        .map(RepositoryConverter::fromEntity)
	        .toList();
	}

	public RepositoryResponseDto  findById(String id) {
		Repository repository = repositoryRepository.findById(id)
		        .orElseThrow(() -> new ResourceNotFoundException(id));
		return RepositoryConverter.fromEntity(repository);
	}

	@Transactional
	public void delete(String id) {
		try {
			repositoryRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	@Transactional
	public RepositoryResponseDto update(String id, RepositoryRequestDto dto) {
		Repository entity = repositoryRepository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException(id));
		updateData(entity, dto);
		Repository updatedEntity = repositoryRepository.save(entity);
		return RepositoryConverter.fromEntity(updatedEntity);
	}

	private void updateData(Repository entity, RepositoryRequestDto dto) {
		// entity.setId(obj.getId());
		entity.setName(dto.name());
		entity.setHtmlUrl(dto.htmlUrl());
		entity.setCreatedAt(dto.createdAt());
		entity.setUpdatedAt(dto.updatedAt());
		entity.setSize(dto.size());
		entity.setStargazers(dto.stargazers());
		entity.setWatchers(dto.watchers());
		entity.setForks(dto.forks());
		entity.setOpenIssues(dto.openIssues());
	}
}
