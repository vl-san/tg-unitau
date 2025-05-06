package com.unitau.tgvinicius.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.unitau.tgvinicius.converter.BranchConverter;
import com.unitau.tgvinicius.dto.response.BranchResponseDto;
import com.unitau.tgvinicius.entities.Branch;
import com.unitau.tgvinicius.repositories.BranchRepository;
import com.unitau.tgvinicius.services.exceptions.DatabaseException;
import com.unitau.tgvinicius.services.exceptions.ResourceNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class BranchService {
	@Autowired
	private BranchRepository branchRepository;

	public List<BranchResponseDto> findAll() {
	    List<Branch> branches = branchRepository.findAll();
	    return branches.stream()
	                   .map(BranchConverter::fromEntity)
	                   .toList();
	}

	public BranchResponseDto findById(String id) {
	    Branch branch = branchRepository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException(id));
	    return BranchConverter.fromEntity(branch);
	}

	@Transactional
	public Branch insert(Branch obj) {
		try {
			return branchRepository.save(obj);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	@Transactional
	public void delete(String id) {
		try {
		branchRepository.deleteById(id);
	} catch (EmptyResultDataAccessException e) {
		throw new ResourceNotFoundException(id);
	} catch (DataIntegrityViolationException e) {
		throw new DatabaseException(e.getMessage());
	}
	}

	public Branch update(String id, Branch obj) {
		Branch entity = branchRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id));
		updateData(entity, obj);
		return branchRepository.save(entity);
	}

	private void updateData(Branch entity, Branch obj) {
		entity.setName(obj.getName());
	}

	@Transactional
	public List<Branch> saveAll(List<Branch> branches) {
		return branchRepository.saveAll(branches);
	}
	
	public List<Branch> findByRepositoryId(String repositoryId) {
	    return branchRepository.findByRepositoryId(repositoryId);
	}

}
