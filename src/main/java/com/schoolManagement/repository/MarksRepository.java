package com.schoolManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.schoolManagement.model.Classes;
import com.schoolManagement.model.Course;
import com.schoolManagement.model.Marks;
import com.schoolManagement.model.User;

public interface MarksRepository extends JpaRepository<Marks, Integer> {

	public Marks findByUser_Name(String name);

	public List<Marks> findByUser(User user);

	public List<Marks> findByClasses(Classes classes);

	public List<Marks> findByCourse(User user);
	
	public List<Marks> deleteByUser(User user);
	
	public Marks deleteByClasses(Classes classes);
	
	public List<Marks> deleteByCourse(Course course);

}
