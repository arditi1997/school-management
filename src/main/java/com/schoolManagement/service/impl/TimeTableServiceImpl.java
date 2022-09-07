package com.schoolManagement.service.impl;

import java.util.List;

import com.schoolManagement.model.Classes;
import com.schoolManagement.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.schoolManagement.repository.TimetableRepository;
import com.schoolManagement.service.TimetableService;
import com.schoolManagement.model.TimeTable;

@Service
public class TimeTableServiceImpl implements TimetableService {

	@Autowired
	TimetableRepository timeTableRepository;
	@Override
	public List<TimeTable> findByCourse_CourseNameLikeAndClasses_ClassNameLikeAndUser_NameLikeAndDayLike(String course, String classes,
			String user, String day) {
		return timeTableRepository.findByCourse_CourseNameLikeAndClasses_ClassNameLikeAndUser_NameLikeAndDayLike(course +"%" , classes +"%", user + "%", day +"%");
	}
	@Override
	public List<TimeTable> findAll() {
		return timeTableRepository.findAll();
	}

	@Override
	public void save(TimeTable timeTable) {
		timeTableRepository.save(timeTable);
	}

	@Override
	public TimeTable getOne(int timetableId) {
		return timeTableRepository.getOne(timetableId);
	}

	@Override
	public List<TimeTable> findByClasses(Classes classes) {
		return timeTableRepository.findByClasses(classes);
	}

	@Override
	public void deleteAll(List<TimeTable> timetable) {
		timeTableRepository.deleteAll();
	}

	@Override
	public List<TimeTable> findByCourse(Course course) {
		return timeTableRepository.findByCourse(course);
	}

	@Override
	public void delete(TimeTable timetable) {
		timeTableRepository.delete(timetable);
	}

}
