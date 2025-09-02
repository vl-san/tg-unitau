package com.unitau.tgvinicius.entities;

import java.time.Duration;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "tb_repository")
public class Repository {

	@Id
	private String id;
	private String name;
	@Column(name = "html_url")
	private String htmlUrl;
	@Column(name = "created_at")
	private Instant createdAt;
	@Column(name = "updated_at")
	private Instant updatedAt;
	private Long size;
	private Integer stargazers;
	private Integer watchers;
	private Integer forks;
	@Column(name = "open_issues")
	private Integer openIssues;

	public void setRepositoryContributors(Set<RepositoryContributor> repositoryContributors) {
		this.repositoryContributors = repositoryContributors;
	}

	@OneToMany(mappedBy = "repository", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<RepositoryContributor> repositoryContributors = new HashSet<>();

	@OneToMany(mappedBy = "repository")
	private Set<Commit> commits = new HashSet<>();

	@OneToMany(mappedBy = "repository")
	private Set<Issue> issues = new HashSet<>();

	public Repository() {
	}

	public Repository(String id, String name, String htmlUrl, Instant createdAt, Instant updatedAt, Long size,
			Integer stargazers, Integer watchers, Integer forks, Integer openIssues) {
		this.id = id;
		this.name = name;
		this.htmlUrl = htmlUrl;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.size = size;
		this.stargazers = stargazers;
		this.watchers = watchers;
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

	public Set<Commit> getCommits() {
		return commits;
	}

	public Set<Issue> getIssues() {
		return issues;
	}

	public Set<RepositoryContributor> getRepositoryContributors() {
		return repositoryContributors;
	}

	public void addCommit(Commit commit) {
		commits.add(commit);
		commit.setRepository(this);
	}

	public void removeCommit(Commit commit) {
		commits.remove(commit);
		commit.setRepository(null);
	}

	public void addIssue(Issue issue) {
		issues.add(issue);
		issue.setRepository(this);
	}

	public void removeBranch(Issue issue) {
		issues.remove(issue);
		issue.setRepository(null);
	}

	public void addRepositoryContributor(RepositoryContributor repositoryContributor) {
		this.repositoryContributors.add(repositoryContributor);
		repositoryContributor.setRepository(this);
	}

	public void removeRepositoryContributor(RepositoryContributor repositoryContributor) {
		this.repositoryContributors.remove(repositoryContributor);
		repositoryContributor.setRepository(null);
	}
	
	@Transient
	public int getTotalIssues() {
	    return issues.size();
	}

	@Transient
	public int getTotalCommits() {
	    return commits.size();
	}

	@Transient
	public int getTotalContributors() {
	    return repositoryContributors.size();
	}
	
	@Transient
	public double getAvgIssuesPerContributor() {
	    int contributors = getTotalContributors();
	    int issues = getTotalIssues();
	    if (contributors == 0) {
	        return 0;
	    }
	    return (double) issues / contributors;
	}

	@Transient
	public double getAvgPercentIssuesPerContributor() {
	    int issues = getTotalIssues();
	    if (issues == 0) {
	        return 0;
	    }
	    double avgIssuesPerContributor = getAvgIssuesPerContributor();
	    return (avgIssuesPerContributor / issues) * 100;
	}
	
	@Transient
	public double getAvgCommitsPerContributor() {
	    int contributors = getTotalContributors();
	    int commits = getTotalCommits();
	    if (contributors == 0) {
	        return 0;
	    }
	    return (double) commits / contributors;
	}

	@Transient
	public double getAvgPercentCommitsPerContributor() {
	    int commits = getTotalCommits();
	    if (commits == 0) {
	        return 0;
	    }
	    double avgCommitsPerContributor = getAvgCommitsPerContributor();
	    return (avgCommitsPerContributor / commits) * 100;
	}
	
	@Transient
	public Duration getInactivityDuration() {
	    return Duration.between(updatedAt, Instant.now());
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Repository other = (Repository) obj;
		return Objects.equals(id, other.id);
	}

}
