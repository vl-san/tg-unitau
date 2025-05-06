package com.unitau.tgvinicius.embeddables;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class RepositoryContributorId implements Serializable {

	private static final long serialVersionUID = 1L;
	private String repositoryId;
	private String contributorId;

	public RepositoryContributorId() {
	}

	public RepositoryContributorId(String repositoryId, String contributorId) {
		this.repositoryId = repositoryId;
		this.contributorId = contributorId;
	}

	public String getRepositoryId() {
		return repositoryId;
	}

	public void setRepositoryId(String repositoryId) {
		this.repositoryId = repositoryId;
	}

	public String getContributorId() {
		return contributorId;
	}

	public void setContributorId(String contributorId) {
		this.contributorId = contributorId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(contributorId, repositoryId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RepositoryContributorId other = (RepositoryContributorId) obj;
		return Objects.equals(contributorId, other.contributorId) && Objects.equals(repositoryId, other.repositoryId);
	}

}