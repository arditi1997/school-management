package com.schoolManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.schoolManagement.repository.AttendanceRepository;
import com.schoolManagement.repository.ClassRepository;
import com.schoolManagement.repository.ExamRepository;
import com.schoolManagement.repository.MarksRepository;
import com.schoolManagement.repository.RoleRepository;
import com.schoolManagement.repository.UserRepository;
import com.schoolManagement.service.ClassesService;
import com.schoolManagement.service.CourseService;
import com.schoolManagement.service.MarkService;
import com.schoolManagement.service.TimetableService;
import com.schoolManagement.service.UserService;
import com.schoolManagement.model.Attendance;
import com.schoolManagement.model.Classes;
import com.schoolManagement.model.Course;
import com.schoolManagement.model.Marks;
import com.schoolManagement.model.User;

@Controller
public class ParentController {
	@Autowired
	private UserService userService;
	@Autowired
	private ClassesService classesService;
	@Autowired
	private MarksRepository marksRepository;
	@Autowired
	CourseService courseService;
	@Autowired
	MarkService markService;
	@Autowired
	private AttendanceRepository attendanceRepository;

	@RequestMapping(value = "/parentHome")
	public ModelAndView parenthome(ModelAndView modelAndView) {

		modelAndView = new ModelAndView("/user/parent/parentHome");

		return modelAndView;
	}
	@RequestMapping("/parentProfile")
	public String parentProfile(User user, Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		user = userService.findByEmail(auth.getName());

		
		model.addAttribute("parent", user);
		model.addAttribute("parentEmail", user.getEmail());
		model.addAttribute("parentName", user.getName());
		model.addAttribute("parentSurname", user.getLastName());
		model.addAttribute("parentAddress", user.getAddress());
		model.addAttribute("parentBirthdate", user.getBirthdate());
		model.addAttribute("parentGender", user.getGender());
		model.addAttribute("parentNationality", user.getNationality());
		model.addAttribute("parentClass", user.getParentOfStudent().getClasses().getClassName());
		model.addAttribute("studentNameOfParent", user.getParentOfStudent().getName());               
		model.addAttribute("studentSurnameOfParent", user.getParentOfStudent().getLastName());             

		return "/user/parent/parentProfile";
	}

	@RequestMapping(value = "/parentExamMarks")
	public ModelAndView viewMarks(ModelAndView modelAndView) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findByEmail(auth.getName());
		
		List<Classes> classesList = classesService.listAllClasses();
		List<Course> coursesList = courseService.ListAllCourses();
		List<Marks> marksList = marksRepository.findByUser(user.getParentOfStudent());

		modelAndView = new ModelAndView("/user/parent/examMark");
		modelAndView.addObject("listOfExamMarks", marksList);
		modelAndView.addObject("classesList", classesList);
		modelAndView.addObject("coursesList", coursesList);
		modelAndView.addObject("parent", user);

		return modelAndView;
	}

	@RequestMapping(value = "/parentAttendance")
	public ModelAndView viewStudentAttendance(ModelAndView modelAndView) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findByEmail(auth.getName());
		
		List<Classes> classesList = classesService.listAllClasses();
		List<Course> coursesList = courseService.ListAllCourses();
		List<Attendance> attendanceList = attendanceRepository.findByUser(user.getParentOfStudent());

		modelAndView = new ModelAndView("/user/parent/attendance");
		modelAndView.addObject("attendancesList", attendanceList);
		modelAndView.addObject("classesList", classesList);
		modelAndView.addObject("coursesList", coursesList);
		modelAndView.addObject("parent", user);

		return modelAndView;
	}
}
