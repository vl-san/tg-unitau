package com.unitau.tgvinicius.entities;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.unitau.tgvinicius.util.CommitDTODeserializer;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@JsonDeserialize(using = CommitDTODeserializer.class)
@Entity
@Table(name = "tb_commit")
public class Commit {

	@Id
	private String sha;
	private String authorName;
	private Instant creation;

	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonBackReference
	private Contributor user;

	@ManyToOne
	@JoinColumn(name = "repository_id")
	private Repository repository;

	public Commit() {
	}

	public Commit(String sha, String authorName, Instant creation, Contributor user, Repository repository) {
		this.sha = sha;
		this.authorName = authorName;
		this.creation = creation;
		this.user = user;
		this.repository = repository;
	}

	public String getSha() {
		return sha;
	}

	public void setSha(String sha) {
		this.sha = sha;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public Instant getCreation() {
		return creation;
	}

	public void setCreation(Instant creation) {
		this.creation = creation;
	}

	public Contributor getUser() {
		return user;
	}

	public void setUser(Contributor user) {
		this.user = user;
	}

	public Repository getRepository() {
		return repository;
	}

	public void setRepository(Repository repository) {
		this.repository = repository;
	}

}
