package com.unitau.tgvinicius.entities;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_user")
public class User {

	@Id
	private String id;
	private String login;
	private String name;

	@ManyToMany
	@JoinTable(name = "user_repository", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "repository_id"))
	private Set<Repository> repository = new HashSet<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private Set<Commit> commitsList = new HashSet<>();

	public User() {
	}

	public User(String id, String login, String name) {
		this.id = id;
		this.login = login;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Repository> getRepository() {
		return repository;
	}

	public Set<Commit> getCommitsList() {
		return commitsList;
	}
}
