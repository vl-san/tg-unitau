package com.unitau.tgvinicius.dto;

import java.time.LocalDate;

public class CommitDTO {
	private String id;
	private String authorName;
	private LocalDate creation;

	public CommitDTO() {
	}

	public CommitDTO(String id, String authorName, LocalDate creation) {
		this.id = id;
		this.authorName = authorName;
		this.creation = creation;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public LocalDate getCreation() {
		return creation;
	}

	public void setCreation(LocalDate creation) {
		this.creation = creation;
	}

}
