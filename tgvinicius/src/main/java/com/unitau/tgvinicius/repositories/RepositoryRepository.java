package com.unitau.tgvinicius.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unitau.tgvinicius.entities.Repository;

public interface RepositoryRepository extends JpaRepository<Repository, String> {

}
