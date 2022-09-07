package com.schoolManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.schoolManagement.model.Attendance;
import com.schoolManagement.model.Classes;
import com.schoolManagement.model.User;

public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {

	public List<Attendance> findByUser(User user);
	
	public List<Attendance> deleteByUser(User user);

	public List<Attendance> findByClasses(Classes Classes);
	
	
	
}
