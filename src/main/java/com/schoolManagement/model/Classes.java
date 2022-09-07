package com.schoolManagement.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "classes")
public class Classes {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "classId")
	private String classId;
	@Column(name = "className")
	private String className;
	@Column(name = "status")
	private String status;
	
	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.DETACH })
	@JoinTable(name = "classesCourse", joinColumns = @JoinColumn(name = "classId"), inverseJoinColumns = @JoinColumn(name = "courseId"))
	private List<Course> course = new ArrayList<Course>();
	
	@ManyToOne
	@JoinColumn
	private User user;
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void removeCourse(Course courseTeacher) {
		course.remove(courseTeacher);
		courseTeacher.getClasses().remove(this);
	}

	public void remove() {
		
		for(Course courseTeacher : new ArrayList<>(course))
			removeCourse(courseTeacher);
	}
	
	public List<Course> getCourse() {
		return course;
	}

	public void setCourse(List<Course> course) {
		this.course = course;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Classes() {
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

}
