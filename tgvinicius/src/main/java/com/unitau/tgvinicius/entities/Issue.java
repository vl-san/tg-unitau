package com.unitau.tgvinicius.entities;

import java.time.Instant;

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
	private String title;
	@Enumerated(EnumType.STRING)
	private IssueState state;
	private Instant createdAt;
	private Instant updatedAt;

	@ManyToOne
	@JoinColumn(name = "repository_id")
	private Repository repository;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private Contributor user;

	public Issue() {
	}

	public Issue(String id, String name, IssueState state, Instant createdAt, Instant updatedAt,
			Repository repository, Contributor user) {
		this.id = id;
		this.title = name;
		this.state = state;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.repository = repository;
		this.user = user;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public IssueState getState() {
		return state;
	}

	public void setState(IssueState state) {
		this.state = state;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}

	public Instant getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Instant updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Repository getRepository() {
		return repository;
	}

	public void setRepository(Repository repository) {
		this.repository = repository;
	}

	public Contributor getUser() {
		return user;
	}

	public void setUser(Contributor user) {
		this.user = user;
	}

}
