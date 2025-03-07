package com.unitau.tgvinicius.dto;

public class UserDTO {
	private String id;
	private String login;
	private String name;

	public UserDTO() {
	}

	public UserDTO(String id, String login, String name) {
		this.id = id;
		this.login = login;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
