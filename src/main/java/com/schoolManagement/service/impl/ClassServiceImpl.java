package com.schoolManagement.service.impl;

import java.util.List;

import com.schoolManagement.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.schoolManagement.repository.ClassRepository;
import com.schoolManagement.service.ClassesService;
import com.schoolManagement.model.Classes;
@Service
public class ClassServiceImpl implements ClassesService{

	@Autowired
	ClassRepository classesRepository;

	@Override
	public boolean isUserAlreadyPresent(Classes classes) {
		boolean isUserAlreadyExists = false;
		Classes existingClass = classesRepository.findByClassName(classes.getClassName());
		// If user is found in database, then user already exists.
		if (existingClass != null) {
			isUserAlreadyExists = true;
		}
		return isUserAlreadyExists;
	}

	@Override
	public Classes Save(Classes classes) {
		return classesRepository.save(classes);
	}

	@Override
	public List<Classes> listAllClasses() {
		return classesRepository.findAll();
	}
	
	public List<Classes> findByClassNameLike(String name){
		
		return classesRepository.findByClassNameLike(name + "%");
	}

	@Override
	public List<Classes> findByStatus(String status) {
		return classesRepository.findByStatus(status);
	}

	@Override
	public Classes findByUser(User user) {
		return classesRepository.findByUser(user);
	}

	@Override
	public Classes getOne(String classes) {
		return classesRepository.getOne(classes);
	}

	@Override
	public void save(Classes classes) {
		classesRepository.save(classes);
	}

	@Override
	public List<Classes> findAllById(List<String> classId) {
		return classesRepository.findAllById(classId);
	}

	@Override
	public Classes findByUserAndStatus(User user, String status) {
		return classesRepository.findByUserAndStatus(user, status);
	}
}
