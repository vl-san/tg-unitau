package com.unitau.tgvinicius.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unitau.tgvinicius.entities.Contributor;
import com.unitau.tgvinicius.repositories.ContributorRepository;

@Service
public class ContributorService {

	@Autowired
	private ContributorRepository contributorRepository;

	public List<Contributor> findAll() {
		return contributorRepository.findAll();
	}

	public Contributor findById(String id) {
		Optional<Contributor> obj = contributorRepository.findById(id);
		return obj.get();
	}

	public Contributor insert(Contributor obj) {
		return contributorRepository.save(obj);
	}

	public void delete(String id) {
		contributorRepository.deleteById(id);
	}

	public Contributor update(String id, Contributor obj) {
		Contributor entity = contributorRepository.getReferenceById(id);
		updateData(entity, obj);
		return contributorRepository.save(entity);
	}

	private void updateData(Contributor entity, Contributor obj) {
		//entity.setId(obj.getId());
		entity.setName(obj.getName());
	}
	
	public List<Contributor> saveAll(List<Contributor> contributors) {
        return contributorRepository.saveAll(contributors);
    }
}
