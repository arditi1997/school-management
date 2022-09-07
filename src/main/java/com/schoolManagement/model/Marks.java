package com.schoolManagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "marks")
public class Marks {
	@Id
	@GeneratedValue
	@Column(name = "markId")
	private int markId;
	@Column(name = "pointsEarned")
	private int pointsEarned;
	@Column(name = "markvalue")
	private int markValue;

	@ManyToOne
	@JoinColumn
	private Classes classes;
	
	@ManyToOne
	@JoinColumn
	private Course course;

	@ManyToOne
	@JoinColumn
	private User user;
	
	public Classes getClasses() {
		return classes;
	}

	public void setClasses(Classes classes) {
		this.classes = classes;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Marks() {
	}

	public int getMarkId() {
		return markId;
	}

	public void setMarkId(int markId) {
		this.markId = markId;
	}

	public int getPointsEarned() {
		return pointsEarned;
	}

	public void setPointsEarned(int pointsEarned) {
		this.pointsEarned = pointsEarned;
	}

	public int getMarkValue() {
		return markValue;
	}

	public void setMarkValue(int markValue) {
		this.markValue = markValue;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
