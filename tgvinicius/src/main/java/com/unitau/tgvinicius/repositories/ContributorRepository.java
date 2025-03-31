package com.unitau.tgvinicius.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unitau.tgvinicius.entities.Contributor;

public interface ContributorRepository extends JpaRepository<Contributor, String> {

}
