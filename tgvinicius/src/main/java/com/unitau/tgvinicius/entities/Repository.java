package com.unitau.tgvinicius.entities;

import java.time.Instant;
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
	private String htmlUrl;
	private Instant created;
	private Instant updated;
	private Long size;
	private Integer stargazers;
	private Integer watchers;
	private String language;
	private Integer forks;
	private Integer openIssues;

	@ManyToMany(mappedBy = "repository")
	private Set<User> users = new HashSet<>();

	@OneToMany(mappedBy = "repository", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Branch> branches = new HashSet<>();

	@OneToMany(mappedBy = "repository", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Commit> commits = new HashSet<>();

	@OneToMany(mappedBy = "repository", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Issue> issues = new HashSet<>();

	public Repository() {
	}

	public Repository(String id, String name, String htmlUrl, Instant created, Instant updated, Long size,
			Integer stargazers, Integer watchers, String language, Integer forks, Integer openIssues,
			Integer subscribers) {
		this.id = id;
		this.name = name;
		this.htmlUrl = htmlUrl;
		this.created = created;
		this.updated = updated;
		this.size = size;
		this.stargazers = stargazers;
		this.watchers = watchers;
		this.language = language;
		this.forks = forks;
		this.openIssues = openIssues;
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

	public String getHtmlUrl() {
		return htmlUrl;
	}

	public void setHtmlUrl(String htmlUrl) {
		this.htmlUrl = htmlUrl;
	}

	public Instant getCreated() {
		return created;
	}

	public void setCreated(Instant created) {
		this.created = created;
	}

	public Instant getUpdated() {
		return updated;
	}

	public void setUpdated(Instant updated) {
		this.updated = updated;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public Integer getStargazers() {
		return stargazers;
	}

	public void setStargazers(Integer stargazers) {
		this.stargazers = stargazers;
	}

	public Integer getWatchers() {
		return watchers;
	}

	public void setWatchers(Integer watchers) {
		this.watchers = watchers;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
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

	public Set<User> getUsers() {
		return users;
	}

	public Set<Branch> getBranches() {
		return branches;
	}

	public Set<Commit> getCommits() {
		return commits;
	}

	public Set<Issue> getIssues() {
		return issues;
	}

}
