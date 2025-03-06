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
//Precisa ter ID em um branch?
	public Branch findById(String id) {
		Optional<Branch> obj = branchRepository.findById(id);
		return obj.get();
	}
}
