package com.schoolManagement.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "course")
public class Course {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "courseId")
	private String courseId;
	@Column(name = "courseName")
	private String courseName;
	@Column(name = "courseCredit")
	private String courseCredit;

	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.DETACH})
	@JoinTable(name = "classesCourse", joinColumns = @JoinColumn(name = "courseId"), inverseJoinColumns = @JoinColumn(name = "classId"))
	private List<Classes> classes = new ArrayList<Classes>();

	@ManyToOne
	@JoinColumn
	private User user;

	public void removeClasses(Classes classesTeacher) {
		classes.remove(classesTeacher);
		classesTeacher.getCourse().remove(this);
	}

	public void remove() {
		
		for(Classes classesTeacher : new ArrayList<>(classes))
			removeClasses(classesTeacher);
	}

	public void addClasses(Classes classesCourse) {
		classes.add(classesCourse);
		classesCourse.getCourse().add(this);
	}

	public void addClassesList(List<Classes> classesCourse) {
		classes.addAll(classesCourse);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Classes> getClasses() {
		return classes;
	}

	public void setClasses(List<Classes> classes) {
		this.classes = classes;
	}

	public Course() {
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseCredit() {
		return courseCredit;
	}

	public void setCourseCredit(String courseCredit) {
		this.courseCredit = courseCredit;
	}

}
