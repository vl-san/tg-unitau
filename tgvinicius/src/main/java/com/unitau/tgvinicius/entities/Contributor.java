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
public class Contributor {

	@Id
	private String id;
	private String name;
	private Integer contributions;
	private String url;

	@ManyToMany
	@JoinTable(name = "user_repository", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "repository_id"))
	private Set<Repository> repository = new HashSet<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private Set<Commit> commitsList = new HashSet<>();

	public Contributor() {
	}

	public Contributor(String id, String name, Integer constributions, String url) {
		this.id = id;
		this.name = name;
		this.contributions = constributions;
		this.url = url;
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

	public Integer getContributions() {
		return contributions;
	}

	public void setContributions(Integer contributions) {
		this.contributions = contributions;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Set<Repository> getRepository() {
		return repository;
	}

	public Set<Commit> getCommitsList() {
		return commitsList;
	}

}
