package com.unitau.tgvinicius.entities;

import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_commit")
public class Commit {

	@Id
	@Column(unique = true, nullable = false)
	private String sha;
	@Column(name = "created_at")
	private Instant createdAt;

	@ManyToOne
	@JoinColumn(name = "repository_id")
	private Repository repository;

	@ManyToOne
	@JoinColumn(name = "contributor_id")
	private Contributor contributor;

	@OneToMany(mappedBy = "commit")
	private Set<Branch> branches = new HashSet<>();

	public Commit() {
	}

	public Commit(String sha, Instant createdAt, Repository repository, Contributor contributor) {
		this.sha = sha;
		this.createdAt = createdAt;
		this.repository = repository;
		this.contributor = contributor;
	}

	public String getSha() {
		return sha;
	}

	public void setSha(String sha) {
		this.sha = sha;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}

	public Repository getRepository() {
		return repository;
	}

	public void setRepository(Repository repository) {
		this.repository = repository;
	}

	public Contributor getContributor() {
		return contributor;
	}

	public void setContributor(Contributor contributor) {
		this.contributor = contributor;
	}

	public Set<Branch> getBranches() {
		return branches;
	}

	public void addBranch(Branch branch) {
		branches.add(branch);
		branch.setCommit(this);
	}

	public void removeBranch(Branch branch) {
		branches.remove(branch);
		branch.setCommit(null);
	}

	@Override
	public int hashCode() {
		return Objects.hash(sha);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Commit other = (Commit) obj;
		return Objects.equals(sha, other.sha);
	}

}
