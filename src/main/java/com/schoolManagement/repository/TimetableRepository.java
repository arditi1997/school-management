package com.schoolManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.schoolManagement.model.Classes;
import com.schoolManagement.model.Course;
import com.schoolManagement.model.TimeTable;
import com.schoolManagement.model.User;

public interface TimetableRepository extends JpaRepository<TimeTable, Integer> {
	
	public List<TimeTable> findByCourse_CourseNameLikeAndClasses_ClassNameLikeAndUser_NameLikeAndDayLike(String course, String classes, String user, String day);

	public TimeTable findByUser(User user);
	
	public List<TimeTable> findByClasses(Classes classes);

	public List<TimeTable> findByCourse(Course course);
}
