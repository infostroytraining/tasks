package com.example.dto;

public class AnswerDTO {

	private String name;
	private String language;

	public AnswerDTO(String name, String language) {
		this.name = name;
		this.language = language;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
}
