package com.schoolManagement.service;

import java.util.List;

import com.schoolManagement.model.Course;

public interface CourseService {

	public Course save(Course course);
	
	public List<Course> ListAllCourses();
	
	public List<Course> findByCourseNameLike(String name);

	Course getOne(String courseId);

	List<Course> findByClasses_ClassId(String classId);

	void delete(Course course);

	List<Course> findAll();
}
