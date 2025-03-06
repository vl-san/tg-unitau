package com.unitau.tgvinicius.dto;

import java.time.LocalDate;

import com.unitau.tgvinicius.enums.IssueState;

public class IssueDTO {
	private String id;
	private String name;
	private IssueState state;
	private LocalDate creation;

	public IssueDTO() {
	}

	public IssueDTO(String id, String name, IssueState state, LocalDate creation) {
		this.id = id;
		this.name = name;
		this.state = state;
		this.creation = creation;
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

	public IssueState getState() {
		return state;
	}

	public void setState(IssueState state) {
		this.state = state;
	}

	public LocalDate getCreation() {
		return creation;
	}

	public void setCreation(LocalDate creation) {
		this.creation = creation;
	}

}
