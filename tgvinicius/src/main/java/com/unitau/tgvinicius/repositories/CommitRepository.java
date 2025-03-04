package com.unitau.tgvinicius.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unitau.tgvinicius.entities.Commit;

public interface CommitRepository extends JpaRepository<Commit, String> {

}
