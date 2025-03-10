package com.unitau.tgvinicius.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unitau.tgvinicius.entities.Repository;
import com.unitau.tgvinicius.repositories.RepositoryRepository;

@Service
public class RepositoryService {

	@Autowired
	private RepositoryRepository repositoryRepository;

	public List<Repository> findAll() {
		return repositoryRepository.findAll();
	}

	public Repository findById(String id) {
		Optional<Repository> obj = repositoryRepository.findById(id);
		return obj.get();
	}

	public Repository insert(Repository obj) {
		return repositoryRepository.save(obj);
	}

	public void delete(String id) {
		repositoryRepository.deleteById(id);
	}

	public Repository update(String id, Repository obj) {
		Repository entity = repositoryRepository.getReferenceById(id);
		updateData(entity, obj);
		return repositoryRepository.save(entity);
	}

	private void updateData(Repository entity, Repository obj) {
		// entity.setId(obj.getId());
		entity.setName(obj.getName());
		entity.setDescription(obj.getDescription());
		entity.setStars(obj.getStars());
		entity.setForks(obj.getForks());
		entity.setOpenIssues(obj.getOpenIssues());
		entity.setCreation(obj.getCreation());
		entity.setLastUpdate(obj.getLastUpdate());
	}
}
