package com.schoolManagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.schoolManagement.repository.CourseRepository;
import com.schoolManagement.service.CourseService;
import com.schoolManagement.model.Course;
@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	CourseRepository courseRepository;

	@Override
	public Course save(Course course) {
		return courseRepository.save(course);
	}

	@Override
	public List<Course> ListAllCourses() {
		return courseRepository.findAll();
	}

	@Override
	public List<Course> findByCourseNameLike(String name) {
		return courseRepository.findByCourseNameLike(name +"%");
	}

	@Override
	public Course getOne(String courseId) {
		return courseRepository.getOne(courseId);
	}

	@Override
	public List<Course> findByClasses_ClassId(String classId) {
		return courseRepository.findByClasses_ClassId(classId);
	}

	@Override
	public void delete(Course course) {
		courseRepository.delete(course);
	}

	@Override
	public List<Course> findAll() {
		return courseRepository.findAll();
	}

}
