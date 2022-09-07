package com.schoolManagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="exam")
public class Exams {
	@Id
	@GeneratedValue
	@Column(name="examId")
	private int examId;
	@Column(name="pointsEarned")
	private int pointsEarned;
	@Column(name="pointsPossible")
	private int pointsPossible;
	@Column(name="totalEarned")
	private int totalEarned;
	@Column(name="totalPossible")
	private int totalPossible;
	@Column(name="examDate")
	private String examDate;
	
	@ManyToOne
	@JoinColumn
	private Course course;
	
	@ManyToOne
	@JoinColumn
	private Classes classes;
	
	@ManyToOne
	@JoinColumn
	private User user;
 

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Exams() {
	}

	public int getExamId() {
		return examId;
	}

	public void setExamId(int examId) {
		this.examId = examId;
	}

	public int getPointsEarned() {
		return pointsEarned;
	}

	public void setPointsEarned(int pointsEarned) {
		this.pointsEarned = pointsEarned;
	}

	public int getPointsPossible() {
		return pointsPossible;
	}

	public void setPointsPossible(int pointsPossible) {
		this.pointsPossible = pointsPossible;
	}

	public int getTotalEarned() {
		return totalEarned;
	}

	public void setTotalEarned(int totalEarned) {
		this.totalEarned = totalEarned;
	}

	public int getTotalPossible() {
		return totalPossible;
	}

	public void setTotalPossible(int totalPossible) {
		this.totalPossible = totalPossible;
	}

	public String getExamDate() {
		return examDate;
	}

	public void setExamDate(String examDate) {
		this.examDate = examDate;
	}

	public Classes getClasses() {
		return classes;
	}

	public void setClasses(Classes classes) {
		this.classes = classes;
	}
	
}
