package com.unitau.tgvinicius.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_repository")
public class Repository {

	@Id
	private String id;
	private String name;
	private String description;
	private Integer stars;
	private Integer forks;
	private Integer openIssues;
	private LocalDate creation;
	private LocalDate lastUpdate;

	@ManyToMany(mappedBy = "repository")
    private Set<User> users = new HashSet<>();

	@OneToMany(mappedBy = "repository", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Branch> branches = new HashSet<>();
	
	@OneToMany(mappedBy = "repository", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Commit> commitsList = new HashSet<>();
	
	@OneToMany(mappedBy = "repository", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Issue> issuesList = new HashSet<>();
	
	public Repository(String id, String name, String description, Integer stars, Integer forks,
			Integer openIssues, LocalDate creation, LocalDate lastUpdate) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.stars = stars;
		this.forks = forks;
		this.openIssues = openIssues;
		this.creation = creation;
		this.lastUpdate = lastUpdate;
	}
	
	public Repository() {
		
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getStars() {
		return stars;
	}

	public void setStars(Integer stars) {
		this.stars = stars;
	}

	public Integer getForks() {
		return forks;
	}

	public void setForks(Integer forks) {
		this.forks = forks;
	}

	public Integer getOpenIssues() {
		return openIssues;
	}

	public void setOpenIssues(Integer openIssues) {
		this.openIssues = openIssues;
	}

	public LocalDate getCreation() {
		return creation;
	}

	public void setCreation(LocalDate creation) {
		this.creation = creation;
	}

	public LocalDate getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(LocalDate lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
}
