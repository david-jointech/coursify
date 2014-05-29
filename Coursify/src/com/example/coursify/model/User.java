package com.example.coursify.model;

import java.util.ArrayList;

public class User {
	private ArrayList<Lecture> lectures;

	public ArrayList<Lecture> getLectures() {
		return lectures;
	}

	public void setLectures(ArrayList<Lecture> lectures) {
		this.lectures = lectures;
	}
}
