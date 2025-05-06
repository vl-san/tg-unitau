package com.unitau.tgvinicius.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.unitau.tgvinicius.entities.Branch;

public interface BranchRepository extends JpaRepository<Branch, String> {
	
    @Query("SELECT b FROM Branch b WHERE b.commit.repository.id = :repositoryId")
    List<Branch> findByRepositoryId(@Param("repositoryId") String repositoryId);
}
