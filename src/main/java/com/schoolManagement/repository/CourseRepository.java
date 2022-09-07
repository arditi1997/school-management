package com.schoolManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.schoolManagement.model.Course;
import com.schoolManagement.model.User;

public interface CourseRepository  extends JpaRepository<Course, String>{
	
	public List<Course> findByCourseNameLike(String name);
	
	public List<Course> findByClasses_ClassId(String courseId);
	
	public List<Course> findByUser(User user);
	
	public List<Course> deleteByUser(User user);

}
