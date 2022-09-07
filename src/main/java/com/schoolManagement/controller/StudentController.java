package com.schoolManagement.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.schoolManagement.repository.ExamRepository;
import com.schoolManagement.repository.MarksRepository;
import com.schoolManagement.repository.PostRepository;
import com.schoolManagement.repository.RoleRepository;
import com.schoolManagement.repository.UserRepository;
import com.schoolManagement.service.ClassesService;
import com.schoolManagement.service.CourseService;
import com.schoolManagement.service.MarkService;
import com.schoolManagement.service.TimetableService;
import com.schoolManagement.service.UserService;
import com.schoolManagement.model.Classes;
import com.schoolManagement.model.Course;
import com.schoolManagement.model.Marks;
import com.schoolManagement.model.Post;
import com.schoolManagement.model.TimeTable;
import com.schoolManagement.model.User;

@Controller
public class StudentController {
	@Autowired
	private UserService userService;
	@Autowired
	private ClassesService classesService;
	@Autowired
	private TimetableService timeTableService;
	@Autowired
	private MarksRepository marksRepository;
	@Autowired
	CourseService courseService;
	@Autowired
	MarkService markService;
	@Autowired
	PostRepository postRepository;
	private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:MM:SS");
	@RequestMapping(value = "/studentHome")
	public ModelAndView studenthome(User user, ModelAndView modelAndView) {
		List<Post> posts = postRepository.findAll();
		modelAndView = new ModelAndView("/user/student/studentHome");
		modelAndView.addObject("postList", posts);

		return modelAndView;
	}

	@RequestMapping("/studentProfile")
	public String studentProfile(User user, Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		user = userService.findByEmail(auth.getName());

		model.addAttribute("student", user);
		model.addAttribute("studentEmail", user.getEmail());
		model.addAttribute("studentName", user.getName());
		model.addAttribute("studentSurname", user.getLastName());
		model.addAttribute("studentAddress", user.getAddress());
		model.addAttribute("studentAddmissionYear", user.getAddmissionYear());
		model.addAttribute("studentBirthdate", user.getBirthdate());
		model.addAttribute("studentGender", user.getGender());
		model.addAttribute("studentNationality", user.getNationality());
		model.addAttribute("studentClass", user.getClasses().getClassName());

		return "/user/student/studentProfile";
	}

	@RequestMapping(value = "/studentTimetable")
	public ModelAndView studentTimetablePage(ModelAndView modelAndView) {

		modelAndView = new ModelAndView("/user/student/studentTimetable");
		List<TimeTable> timeTable = timeTableService.findAll();
		modelAndView.addObject("timetableList", timeTable);

		return modelAndView;
	}

	@RequestMapping(value = "/studentSearch{course}{classes}{user}{day}", method = RequestMethod.GET)
	public ModelAndView studentTimetablePageSearch(ModelAndView modelAndView, @RequestParam("day") String day,
			@RequestParam("class") String classes, @RequestParam(value = "course", required = false) String course,
			@RequestParam("user") String user) {

		modelAndView.setViewName("/user/student/studentTimetable");
		List<TimeTable> timetable = timeTableService
				.findByCourse_CourseNameLikeAndClasses_ClassNameLikeAndUser_NameLikeAndDayLike(course, classes, user,
						day);
		modelAndView.addObject("timetableList", timetable);
		System.out.println(timetable);
		return modelAndView;
	}

//	 View their marks per subject. 

	@RequestMapping(value = "/studentExamMarks")
	public ModelAndView viewMarks(ModelAndView modelAndView) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findByEmail(auth.getName());

		List<Classes> classesList = classesService.listAllClasses();
		List<Course> coursesList = courseService.ListAllCourses();
		List<Marks> marksList = marksRepository.findByUser(user);

		modelAndView = new ModelAndView("/user/student/examMark");
		modelAndView.addObject("listOfExamMarks", marksList);
		modelAndView.addObject("classesList", classesList);
		modelAndView.addObject("coursesList", coursesList);
		modelAndView.addObject("student", user);

		return modelAndView;
	}

	@RequestMapping(value = "/myStudentPosts")
	public ModelAndView studentPostsPage(ModelAndView modelAndView) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findByEmail(auth.getName());
		List<Post> posts = postRepository.findByUser(user);
		modelAndView = new ModelAndView("/user/student/myPosts");
		modelAndView.addObject("postList", posts);

		return modelAndView;
	}
	@RequestMapping(value = "/addStudentPost", method = RequestMethod.GET)
	public String addNewPostStudent(Model model) {

		Post post = new Post();
		model.addAttribute("post", post);

		return "/user/student/addPost";
	}

	@RequestMapping(value = "/addStudentPost", method = RequestMethod.POST)
	public String updatePostStudent(@Valid Post post, Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findByEmail(auth.getName());
		Date date = new Date();
		post.setPostCreated(sdf.format(date));
		post.setUser(user);
		postRepository.save(post);
		return "redirect:/addPost";
	}

	@RequestMapping("/openStudentPost/{postId}")
	public String OpenPostStudent(Model model, Post post, @PathVariable int postId) {

		post = postRepository.getOne(postId);

		model.addAttribute("postName", post.getPostName());
		model.addAttribute("postText", post.getPostText());
		model.addAttribute("postCreated", post.getPostCreated());
		model.addAttribute("postId", post.getPostId());

		return "/user/student/textPost";
	}

	@RequestMapping(value = "/saveStudentPostText")
	public String savePostTextStudent(Model model, Post post, @RequestParam("postText") String text,
			@RequestParam("saveText") int postId) {

		post = postRepository.getOne(postId);
		post.setPostText(text);
		postRepository.save(post);

		return "redirect:/openPost/" + postId;
	}
	
	@RequestMapping(value = "/openStudentPostHome/{postId}")
	public String openPostHomeStudent(Model model, Post post,@PathVariable("postId") int postId) {

		post= postRepository.getOne(postId);
		model.addAttribute("postName", post.getPostName());
		model.addAttribute("postText", post.getPostText());
		model.addAttribute("postUserName", post.getUser().getName());
		model.addAttribute("postUserSurname", post.getUser().getLastName());
		
		

		return "user/student/openPostHome";
	}
}
