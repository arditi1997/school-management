package com.schoolManagement.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import com.schoolManagement.service.AttendanceService;
import com.schoolManagement.service.ExamService;
import com.schoolManagement.service.PostService;
import com.schoolManagement.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.schoolManagement.repository.AttendanceRepository;
import com.schoolManagement.service.ClassesService;
import com.schoolManagement.service.CourseService;
import com.schoolManagement.service.MarkService;
import com.schoolManagement.service.TimetableService;
import com.schoolManagement.service.UserService;
import com.schoolManagement.model.Attendance;
import com.schoolManagement.model.Classes;
import com.schoolManagement.model.Course;
import com.schoolManagement.model.Exams;
import com.schoolManagement.model.Marks;
import com.schoolManagement.model.Post;
import com.schoolManagement.model.Role;
import com.schoolManagement.model.TimeTable;
import com.schoolManagement.model.User;

@Controller
public class TeacherController {
	@Autowired
	private MarkService markService;
	@Autowired
	private ClassesService classesService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserService userService;
	@Autowired
	private TimetableService timeTableService;
	@Autowired
	private ExamService examService;
	@Autowired
	CourseService courseService;
	@Autowired
	PostService postService;
	@Autowired
	private AttendanceService attendanceService;

	private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:MM:SS");

	@RequestMapping(value = "/teacherHome")
	public ModelAndView teacherhome(ModelAndView modelAndView) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findByEmail(auth.getName());

		List<Post> postList = postService.findAll();

		modelAndView = new ModelAndView("/user/teacher/teacherHome");
		modelAndView.addObject("teacherName", user.getName());
		modelAndView.addObject("teacherSurname", user.getLastName());
		modelAndView.addObject("postList", postList);

		return modelAndView;
	}

	@RequestMapping(value = "/exams")
	public ModelAndView examPage(ModelAndView modelAndView) {

		modelAndView = new ModelAndView("/user/teacher/exams");
		List<Exams> exams = examService.findAll();
		modelAndView.addObject("listOfExams", exams);

		return modelAndView;
	}

	@RequestMapping(value = "/addExams")
	public ModelAndView addExamsPage(ModelAndView modelAndView) {

		List<Classes> classesList = classesService.listAllClasses();
		List<Course> coursesList = courseService.ListAllCourses();
		Exams exams = new Exams();

		modelAndView = new ModelAndView("/user/teacher/addExam");
		modelAndView.addObject("classesList", classesList);
		modelAndView.addObject("coursesList", coursesList);
		modelAndView.addObject("exams", exams);

		return modelAndView;
	}

	@RequestMapping(value = "/addAttendance")
	public ModelAndView addAttendancePage(ModelAndView modelAndView) {

		List<Classes> classesList = classesService.listAllClasses();
		List<Course> coursesList = courseService.ListAllCourses();
		Role studentRole = roleService.findByRole("SITE_STUDENT");
		List<User> studentList = userService.findByRoles(studentRole);
		Attendance attendance = new Attendance();

		modelAndView = new ModelAndView("/user/teacher/addAttendance");
		modelAndView.addObject("classesList", classesList);
		modelAndView.addObject("coursesList", coursesList);
		modelAndView.addObject("studentList", studentList);
		modelAndView.addObject("attendance", attendance);

		return modelAndView;
	}

	@RequestMapping(value = "/attendance")
	public ModelAndView attendancePage(ModelAndView modelAndView) {

		List<Classes> classesList = classesService.listAllClasses();
		List<Course> coursesList = courseService.ListAllCourses();
		List<Attendance> attendanceList = attendanceService.findAll();
		modelAndView = new ModelAndView("/user/teacher/attendance");
		modelAndView.addObject("classesList", classesList);
		modelAndView.addObject("coursesList", coursesList);
		modelAndView.addObject("attendancesList", attendanceList);

		return modelAndView;
	}

	@RequestMapping(value = "/myPosts")
	public ModelAndView postsPage(ModelAndView modelAndView) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findByEmail(auth.getName());
		List<Post> posts = postService.findByUser(user);
		modelAndView = new ModelAndView("/user/teacher/myPosts");
		modelAndView.addObject("postList", posts);

		return modelAndView;
	}

	@RequestMapping(value = "/addExamMarks")
	public ModelAndView addExamMarksPage(ModelAndView modelAndView) {

		List<Classes> classesList = classesService.listAllClasses();
		List<Course> coursesList = courseService.ListAllCourses();
		Role studentRole = roleService.findByRole("SITE_STUDENT");
		List<User> studentList = userService.findByRoles(studentRole);
		Marks marks = new Marks();

		modelAndView = new ModelAndView("/user/teacher/addExamMarks");
		modelAndView.addObject("classesList", classesList);
		modelAndView.addObject("coursesList", coursesList);
		modelAndView.addObject("studentsList", studentList);
		modelAndView.addObject("marks", marks);

		return modelAndView;
	}

	@RequestMapping(value = "/examMarks")
	public ModelAndView examMarksPage(ModelAndView modelAndView) {

		List<Classes> classesList = classesService.listAllClasses();
		List<Course> coursesList = courseService.ListAllCourses();
		Role studentRole = roleService.findByRole("SITE_STUDENT");
		List<User> studentList = userService.findByRoles(studentRole);
		List<Marks> marksList = markService.findAll();

		modelAndView = new ModelAndView("/user/teacher/examMarks");
		modelAndView.addObject("listOfExamMarks", marksList);
		modelAndView.addObject("classesList", classesList);
		modelAndView.addObject("coursesList", coursesList);
		modelAndView.addObject("studentsList", studentList);

		return modelAndView;
	}

	@RequestMapping(value = "/addExamMarks", method = RequestMethod.POST)
	public ModelAndView registerClass(@Valid Marks marks, User user, BindingResult bindingResult, ModelMap modelMap,
			RedirectAttributes atts) throws IOException {

		ModelAndView modelAndView = new ModelAndView();
		// Check for the validations
		if (bindingResult.hasErrors()) {
			return modelAndView;

		}

		// we will save the user if, no binding errors
		else {
			// Create Token and Save
			modelAndView.addObject("successMessage", "Mark is registered successfully!");

			markService.save(marks);
		}
		modelAndView.addObject("marks", new Marks());
		modelAndView.setViewName("/user/teacher/addExamMarks");
		return modelAndView;
	}

	@RequestMapping(value = "/addExams", method = RequestMethod.POST)
	public ModelAndView registerClass(@Valid Exams exams, User user, BindingResult bindingResult, ModelMap modelMap,
			RedirectAttributes atts) throws IOException {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		user = userService.findByEmail(auth.getName());
		ModelAndView modelAndView = new ModelAndView();
		// Check for the validations
		if (bindingResult.hasErrors()) {
			return modelAndView;

		}

		// we will save the user if, no binding errors
		else {
			// Create Token and Save
			modelAndView.addObject("successMessage", "Exam is registered successfully!");

			exams.setUser(user);
			examService.save(exams);
		}
		modelAndView.addObject("exams", new Exams());
		modelAndView.setViewName("/user/teacher/addExam");
		return modelAndView;
	}

	@RequestMapping(value = "/addAttendance", method = RequestMethod.POST)
	public ModelAndView registerAttendance(@Valid Attendance attendance, User user, BindingResult bindingResult,
			ModelMap modelMap, RedirectAttributes atts) throws IOException {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		user = userService.findByEmail(auth.getName());
		ModelAndView modelAndView = new ModelAndView();
		// Check for the validations
		if (bindingResult.hasErrors()) {
			return modelAndView;

		}

		// we will save the user if, no binding errors
		else {
			// Create Token and Save
			modelAndView.addObject("successMessage", "Attendance is registered successfully!");

			attendanceService.save(attendance);
		}
		modelAndView.addObject("attendance", new Attendance());
		modelAndView.setViewName("/user/teacher/addAttendance");
		return modelAndView;
	}

	@RequestMapping("/teacherProfile")
	public String teacherProfile(User user, Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		user = userService.findByEmail(auth.getName());

		Classes classes = classesService.findByUserAndStatus(user, "0");
		System.out.println(classes);
		model.addAttribute("teacherEmail", user.getEmail());
		model.addAttribute("teacherName", user.getName());
		model.addAttribute("teacherSurname", user.getLastName());
		model.addAttribute("teacherAddress", user.getAddress());
		model.addAttribute("teacherAddmissionYear", user.getAddmissionYear());
		model.addAttribute("teacherBirthdate", user.getBirthdate());
		model.addAttribute("teacherGender", user.getGender());
		model.addAttribute("teacherNationality", user.getNationality());
		model.addAttribute("teacherTitle", user.getTitle());
		model.addAttribute("teacherClass", classes.getClassName());

		return "/user/teacher/teacherProfile";
	}

	@RequestMapping(value = "/teacherTimetable")
	public ModelAndView studentTimetablePage(ModelAndView modelAndView) {

		modelAndView = new ModelAndView("/user/teacher/teacherTimetable");
		List<TimeTable> timeTable = timeTableService.findAll();
		modelAndView.addObject("timetableList", timeTable);

		return modelAndView;
	}

	@RequestMapping(value = "/teacherSearch{course}{classes}{user}{day}", method = RequestMethod.GET)
	public ModelAndView studentTimetablePageSearch(ModelAndView modelAndView, @RequestParam("day") String day,
			@RequestParam("class") String classes, @RequestParam(value = "course", required = false) String course,
			@RequestParam("user") String user) {

		modelAndView.setViewName("/user/teacher/teacherTimetable");
		List<TimeTable> timetable = timeTableService
				.findByCourse_CourseNameLikeAndClasses_ClassNameLikeAndUser_NameLikeAndDayLike(course, classes, user,
						day);
		modelAndView.addObject("timetableList", timetable);
		System.out.println(timetable);
		return modelAndView;
	}

	@RequestMapping(value = "/addPost", method = RequestMethod.GET)
	public String addNewPost(Model model) {

		Post post = new Post();
		model.addAttribute("post", post);

		return "/user/teacher/addPost";
	}

	@RequestMapping(value = "/addPost", method = RequestMethod.POST)
	public String updatePost(@Valid Post post, Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findByEmail(auth.getName());
		Date date = new Date();
		post.setPostCreated(sdf.format(date));
		post.setUser(user);
		postService.save(post);
		return "redirect:/addPost";
	}

	@RequestMapping("/openPost/{postId}")
	public String OpenPost(Model model, Post post, @PathVariable int postId) {

		post = postService.getOne(postId);

		model.addAttribute("postName", post.getPostName());
		model.addAttribute("postText", post.getPostText());
		model.addAttribute("postCreated", post.getPostCreated());
		model.addAttribute("postId", post.getPostId());

		return "/user/teacher/textPost";
	}

	@RequestMapping(value = "/savePostText")
	public String savePostText(Model model, @RequestBody Post post, @RequestParam("postText") String text,
							   @RequestParam("saveText") int postId) {

		post = postService.getOne(postId);
		post.setPostText(text);
		postService.save(post);

		return "redirect:/openPost/" + postId;
	}
	
	@RequestMapping(value = "/openPostHome/{postId}")
	public String openPostHome(Model model, @RequestBody Post post,@PathVariable("postId") int postId) {

		post= postService.getOne(postId);
		model.addAttribute("postName", post.getPostName());
		model.addAttribute("postText", post.getPostText());
		model.addAttribute("postUserName", post.getUser().getName());
		model.addAttribute("postUserSurname", post.getUser().getLastName());
		
		

		return "user/teacher/openPostHome";
	}
}
