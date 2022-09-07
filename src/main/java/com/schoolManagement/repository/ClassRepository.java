package com.schoolManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.schoolManagement.model.Classes;
import com.schoolManagement.model.User;

public interface ClassRepository extends JpaRepository<Classes, String>{
	
	public Classes findByClassName(String className);
	
	public Classes findByUser(User user);
	
	public List<Classes> findByClassNameLike(String name);
	
	public List<Classes> findByStatus(String name);
	
	public List<Classes> findByCourse_CourseId(String  courseId);
	
	public Classes findByUserAndStatus(User user,String status);
	
	public User findByUser(Classes classes);

}
