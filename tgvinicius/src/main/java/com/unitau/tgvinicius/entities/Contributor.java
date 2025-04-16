package com.unitau.tgvinicius.entities;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_contributor")
public class Contributor {

	@Id
	private String id;
	private String name;
	private Integer contributions;
	private String url;

	@OneToMany(mappedBy = "contributor")
	private Set<Commit> commits = new HashSet<>();

	@ManyToOne
	@JoinColumn(name = "repository_id")
	private Repository repository;

	@OneToMany(mappedBy = "contributor")
	private Set<Issue> issues = new HashSet<>();

	public Contributor() {
	}

	public Contributor(String id, String name, Integer contributions, String url, Repository repository) {
		this.id = id;
		this.name = name;
		this.contributions = contributions;
		this.url = url;
		this.repository = repository;
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

	public Repository getRepository() {
		return repository;
	}

	public void setRepository(Repository repository) {
		this.repository = repository;
	}

	public Set<Commit> getCommits() {
		return commits;
	}

	public Set<Issue> getIssues() {
		return issues;
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

	public void removeBranch(Issue issue) {
		issues.remove(issue);
		issue.setContributor(null);
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
