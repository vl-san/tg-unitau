package com.unitau.tgvinicius.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unitau.tgvinicius.entities.Commit;

public interface CommitRepository extends JpaRepository<Commit, String> {
	List<Commit> findByRepositoryId(String repositoryId);
}
