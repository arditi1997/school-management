package com.schoolManagement.service;

import com.schoolManagement.model.Classes;
import com.schoolManagement.model.Marks;
import com.schoolManagement.model.User;

import java.util.List;

public interface MarkService {
	
	public boolean isUserAlreadyPresent(User user);

	List<Marks> findByClasses(Classes classes);

	void deleteAll(List<Marks> marks);

	void save(Marks marks);

	List<Marks> findAll();
}
