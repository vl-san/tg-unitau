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

}
