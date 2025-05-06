package com.unitau.tgvinicius.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unitau.tgvinicius.embeddables.RepositoryContributorId;
import com.unitau.tgvinicius.entities.RepositoryContributor;

public interface RepositoryContributorRepository extends JpaRepository<RepositoryContributor, RepositoryContributorId> {
	List<RepositoryContributor> findByIdRepositoryId(String repositoryId);
	List<RepositoryContributor> findByIdContributorId(String contributorId);
}