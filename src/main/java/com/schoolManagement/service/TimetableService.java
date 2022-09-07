package com.schoolManagement.service;

import java.util.List;

import com.schoolManagement.model.Classes;
import com.schoolManagement.model.Course;
import com.schoolManagement.model.TimeTable;

public interface TimetableService {

	public List<TimeTable> findByCourse_CourseNameLikeAndClasses_ClassNameLikeAndUser_NameLikeAndDayLike(String course, String classes,
			String user, String day);
	
	public List<TimeTable> findAll();

	void save(TimeTable timeTable);

	TimeTable getOne(int timetableId);

	List<TimeTable> findByClasses(Classes classes);

	void deleteAll(List<TimeTable> timetable);

	List<TimeTable> findByCourse(Course course);

	void delete(TimeTable timetable);
}
