package com.unitau.tgvinicius.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_branch")
public class Branch {
	@Id
	private String shaCommit;
	private String name;

	@OneToOne
	@JoinColumn(name = "commit_id")
	private Commit lastCommit;

	public Branch() {
	}

	public Branch(String id, String name, Repository repository, Commit lastCommit) {
		this.shaCommit = id;
		this.name = name;
		this.lastCommit = lastCommit;
	}

	public String getShaCommit() {
		return shaCommit;
	}

	public void setShaCommit(String shaCommit) {
		this.shaCommit = shaCommit;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Commit getLastCommit() {
		return lastCommit;
	}

	public void setLastCommit(Commit lastCommit) {
		this.lastCommit = lastCommit;
	}

}
