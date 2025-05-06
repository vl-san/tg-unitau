package com.unitau.tgvinicius.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.unitau.tgvinicius.entities.Contributor;

public interface ContributorRepository extends JpaRepository<Contributor, String> {
	
	@Query("SELECT c FROM Contributor c " +
		       "JOIN RepositoryContributor rc ON rc.contributor = c " +
		       "WHERE rc.repository.id = :repositoryId")
		List<Contributor> findByRepositoryId(@Param("repositoryId") String repositoryId);


}
