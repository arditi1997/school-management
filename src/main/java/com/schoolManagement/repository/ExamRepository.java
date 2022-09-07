package com.schoolManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.schoolManagement.model.Exams;
import com.schoolManagement.model.User;

public interface ExamRepository extends JpaRepository<Exams, Integer> {
	
	public List<Exams> findByUser(User user);

}
