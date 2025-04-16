package com.unitau.tgvinicius.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.unitau.tgvinicius.entities.Commit;
import com.unitau.tgvinicius.repositories.CommitRepository;
import com.unitau.tgvinicius.services.exceptions.DatabaseException;
import com.unitau.tgvinicius.services.exceptions.ResourceNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class CommitService {

	@Autowired
	private CommitRepository commitRepository;

	public List<Commit> findAll() {
		return commitRepository.findAll();
	}

	public Commit findById(String id) {
		Optional<Commit> obj = commitRepository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	@Transactional
	public Commit insert(Commit obj) {
		try {
			return commitRepository.save(obj);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	@Transactional
	public void delete(String id) {
		try {
			commitRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	public Commit update(String id, Commit obj) {
		Commit entity = commitRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id));
		updateData(entity, obj);
		return commitRepository.save(entity);
	}

	private void updateData(Commit entity, Commit obj) {
		// entity.setSha(obj.getSha());
		entity.setAuthorLogin(obj.getAuthorLogin());
		entity.setCreationAt(obj.getCreationAt());
	}

	@Transactional
	public List<Commit> saveAll(List<Commit> commits) {
		return commitRepository.saveAll(commits);
	}
}
