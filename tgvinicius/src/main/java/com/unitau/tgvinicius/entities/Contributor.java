package com.unitau.tgvinicius.entities;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "tb_contributor")
public class Contributor {

	@Id
	private String id;
	private String name;
	private String url;

	@OneToMany(mappedBy = "contributor", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<RepositoryContributor> repositoryContributors = new HashSet<>();

	@OneToMany(mappedBy = "contributor")
	private Set<Commit> commits = new HashSet<>();

	@OneToMany(mappedBy = "contributor")
	private Set<Issue> issues = new HashSet<>();

	public Contributor() {
	}

	public Contributor(String id, String name, String url) {
		this.id = id;
		this.name = name;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	public void addRepositoryContributor(RepositoryContributor repositoryContributor) {
		this.repositoryContributors.add(repositoryContributor);
		repositoryContributor.setContributor(this);
	}

	public void removeRepositoryContributor(RepositoryContributor repositoryContributor) {
		this.repositoryContributors.remove(repositoryContributor);
		repositoryContributor.setContributor(null);
	}

	public void addCommit(Commit commit) {
		commits.add(commit);
		commit.setContributor(this);
	}

	public void removeCommit(Commit commit) {
		commits.remove(commit);
		commit.setContributor(null);
	}

	public void addIssue(Issue issue) {
		issues.add(issue);
		issue.setContributor(this);
	}

	public void removeIssue(Issue issue) {
		issues.remove(issue);
		issue.setContributor(null);
	}
	
	@Transient
	public int getCommitCount() {
	    return commits != null ? commits.size() : 0;
	}

	@Transient
	public int getIssueCount() {
	    return issues != null ? issues.size() : 0;
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
		Contributor other = (Contributor) obj;
		return Objects.equals(id, other.id);
	}

}
