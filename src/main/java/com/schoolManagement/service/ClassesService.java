package com.schoolManagement.service;

import java.util.List;

import com.schoolManagement.model.Classes;
import com.schoolManagement.model.User;

public interface ClassesService  {
	
//	public void addNewClass(Classes classes, String className);
	
//	public Classes editClass(Classes classes);
	
	boolean isUserAlreadyPresent(Classes classes);
	
	public Classes Save(Classes classes);
	
	public List<Classes> listAllClasses();
	
	public List<Classes> findByClassNameLike(String name);

	List<Classes> findByStatus(String status);

	Classes findByUser(User user);

	Classes getOne(String classes);

	void save(Classes classes);

	List<Classes> findAllById(List<String> classId);

	Classes findByUserAndStatus(User user, String s);
}
