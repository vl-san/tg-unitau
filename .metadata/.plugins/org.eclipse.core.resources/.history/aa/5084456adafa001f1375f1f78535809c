package com.unitau.tgvinicius.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unitau.tgvinicius.entities.Commit;
import com.unitau.tgvinicius.repositories.CommitRepository;

@Service
public class CommitService {

	@Autowired
	private CommitRepository commitRepository;

	public List<Commit> findAll() {
		return commitRepository.findAll();
	}

	public Commit findById(String id) {
		Optional<Commit> obj = commitRepository.findById(id);
		return obj.get();
	}
}
