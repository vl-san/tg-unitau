package com.unitau.tgvinicius.entities;

import java.time.LocalDate;

import com.unitau.tgvinicius.enums.IssueState;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_issue")
public class Issue {

	@Id
	private String id;
	private String name;
	@Enumerated(EnumType.STRING)
	private IssueState state;
	private LocalDate creation;

	@ManyToOne
	@JoinColumn(name = "repository_id")
	private Repository repository;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public Issue() {
	}

	public Issue(String id, String name, IssueState state, LocalDate creation, Repository repository, User user) {
		this.id = id;
		this.name = name;
		this.state = state;
		this.creation = creation;
		this.repository = repository;
		this.user = user;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public IssueState getState() {
		return state;
	}

	public void setState(IssueState state) {
		this.state = state;
	}

	public LocalDate getCreation() {
		return creation;
	}

	public void setCreation(LocalDate creation) {
		this.creation = creation;
	}

	public Repository getRepository() {
		return repository;
	}

	public void setRepository(Repository repository) {
		this.repository = repository;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
