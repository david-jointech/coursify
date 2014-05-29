package com.example.coursify.model;

public class Lecture {
	private CharSequence name;

	public Lecture(CharSequence name) {
		this.name = name;
	}

	public CharSequence getName() {
		return name;
	}

	public void setName(CharSequence name) {
		this.name = name;
	}
}
