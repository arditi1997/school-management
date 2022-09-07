package com.schoolManagement.service.impl;

import com.schoolManagement.model.Classes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.schoolManagement.repository.MarksRepository;
import com.schoolManagement.service.MarkService;
import com.schoolManagement.model.Marks;
import com.schoolManagement.model.User;

import java.util.List;

@Service
public class MarkServiceImpl implements MarkService {
	@Autowired
	private MarksRepository markRepository;
	
	public boolean isUserAlreadyPresent(User user) {
		
		boolean isUserAlreadyExists = false;
		Marks marks = markRepository.findByUser_Name(user.getName());
		// If user is found in database, then then user already exists.
		if (marks != null) {
			isUserAlreadyExists = true;
		}
		return isUserAlreadyExists;
	}

	@Override
	public List<Marks> findByClasses(Classes classes) {
		return markRepository.findByClasses(classes);
	}

	@Override
	public void deleteAll(List<Marks> marks) {
		markRepository.deleteAll();
	}

	@Override
	public void save(Marks marks) {
		markRepository.save(marks);
	}

	@Override
	public List<Marks> findAll() {
		return markRepository.findAll();
	}

}
