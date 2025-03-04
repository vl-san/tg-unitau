package com.unitau.tgvinicius.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unitau.tgvinicius.entities.Issue;

public interface IssueRepository extends JpaRepository<Issue, String> {

}
