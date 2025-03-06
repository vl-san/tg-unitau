package com.unitau.tgvinicius.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unitau.tgvinicius.entities.Branch;
import com.unitau.tgvinicius.repositories.BranchRepository;

@Service
public class BranchService {
	@Autowired
	private BranchRepository branchRepository;

	public List<Branch> findAll() {
		return branchRepository.findAll();
	}

	public Branch findById(String id) {
		Optional<Branch> obj = branchRepository.findById(id);
		return obj.get();
	}

	public Branch insert(Branch obj) {
		return branchRepository.save(obj);
	}

	public void delete(String id) {
		branchRepository.deleteById(id);
	}

	public Branch update(String id, Branch obj) {
		Branch entity = branchRepository.getReferenceById(id);
		updateData(entity, obj);
		return branchRepository.save(entity);
	}

	private void updateData(Branch entity, Branch obj) {
		// entity.setId(obj.getId());
		entity.setName(obj.getName());
	}
}
