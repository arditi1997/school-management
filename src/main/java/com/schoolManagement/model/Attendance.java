package com.schoolManagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "attendance")
public class Attendance {
	@Id
	@GeneratedValue
	@Column(name = "attendanceId")
	private int attendanceId;
	@Column(name = "attendancevalue")
	private String attendanceValue;

	@ManyToOne
	@JoinColumn
	private User user;
	
	@ManyToOne
	@JoinColumn
	private Classes classes;
	
	@ManyToOne
	@JoinColumn
	private Course course;

	public Attendance() {
	}

	public int getAttendanceId() {
		return attendanceId;
	}

	public void setAttendanceId(int attendanceId) {
		this.attendanceId = attendanceId;
	}

	public String getAttendanceValue() {
		return attendanceValue;
	}

	public void setAttendanceValue(String attendanceValue) {
		this.attendanceValue = attendanceValue;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

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

}
