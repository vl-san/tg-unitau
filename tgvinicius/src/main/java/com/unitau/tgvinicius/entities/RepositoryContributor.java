package com.unitau.tgvinicius.entities;

import java.util.Objects;

import com.unitau.tgvinicius.embeddables.RepositoryContributorId;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "tb_repository_contributor")
public class RepositoryContributor {

	@EmbeddedId
	private RepositoryContributorId id = new RepositoryContributorId();

	@ManyToOne
	@MapsId("repositoryId")
	@JoinColumn(name = "repository_id")
	private Repository repository;

	@ManyToOne
	@MapsId("contributorId")
	@JoinColumn(name = "contributor_id")
	private Contributor contributor;

	private Integer contributions;

	public RepositoryContributor() {
	}

	public RepositoryContributor(Repository repository, Contributor contributor, Integer contributions) {
		this.repository = repository;
		this.contributor = contributor;
		this.contributions = contributions;
		this.id = new RepositoryContributorId(repository.getId(), contributor.getId());
	}

	public RepositoryContributorId getId() {
		return id;
	}

	public void setId(RepositoryContributorId id) {
		this.id = id;
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

	public Integer getContributions() {
		return contributions;
	}

	public void setContributions(Integer contributions) {
		this.contributions = contributions;
	}

	public String getRepositoryId() {
		return id != null ? id.getRepositoryId() : null;
	}

	public String getContributorId() {
		return id != null ? id.getContributorId() : null;
	}
	
	@Transient
	public int getCommitsCount() {
	    if (repository == null || repository.getCommits() == null) return 0;

	    return (int) repository.getCommits().stream()
	            .filter(commit -> contributor.equals(commit.getContributor()))
	            .count();
	}

	@Transient
	public int getIssuesCount() {
	    if (repository == null || repository.getIssues() == null) return 0;

	    return (int) repository.getIssues().stream()
	            .filter(issue -> contributor.equals(issue.getContributor()))
	            .count();
	}

	@Transient
	public double getPercentCommits() {
	    int totalCommits = repository != null ? repository.getTotalCommits() : 0;
	    int contributorCommits = getCommitsCount();
	    return totalCommits == 0 ? 0 : (double) contributorCommits / totalCommits * 100;
	}

	@Transient
	public double getPercentIssues() {
	    int totalIssues = repository != null ? repository.getTotalIssues() : 0;
	    int contributorIssues = getIssuesCount();
	    return totalIssues == 0 ? 0 : (double) contributorIssues / totalIssues * 100;
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
		RepositoryContributor other = (RepositoryContributor) obj;
		return Objects.equals(id, other.id);
	}

}
