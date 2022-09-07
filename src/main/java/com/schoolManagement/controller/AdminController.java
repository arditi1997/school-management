package com.schoolManagement.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.schoolManagement.service.MarkService;
import com.schoolManagement.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.schoolManagement.repository.AttendanceRepository;
import com.schoolManagement.service.ClassesService;
import com.schoolManagement.service.CourseService;
import com.schoolManagement.service.TimetableService;
import com.schoolManagement.service.UserService;
import com.schoolManagement.model.Attendance;
import com.schoolManagement.model.Classes;
import com.schoolManagement.model.Course;
import com.schoolManagement.model.Marks;
import com.schoolManagement.model.Role;
import com.schoolManagement.model.TimeTable;
import com.schoolManagement.model.User;

@Controller
public class AdminController {

	@Autowired
	private UserService userService;
	@Autowired
	private TimetableService timeTableService;
	@Autowired
	private AttendanceRepository attendanceRepository;
	@Autowired
	private ClassesService classesService;
	@Autowired
	CourseService courseService;
	@Autowired
	private BCryptPasswordEncoder encoder;
	@Autowired
	private RoleService roleService;
	@Autowired
	private MarkService markService;

	@RequestMapping(value = "/adminHome")
	public ModelAndView adminhome() {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findByEmail(auth.getName());

		ModelAndView modelAndView = new ModelAndView("/admin/adminHome");
		Role studentRole = roleService.findByRole("SITE_STUDENT");
		Role teacherRole = roleService.findByRole("SITE_TEACHER");
		List<User> studentsList = userService.findByRoles(studentRole);
		List<Classes> classesList = classesService.findByStatus("0");
		List<User> teachersList = userService.findByRoles(teacherRole);
		List<Course> courseList = courseService.findAll();
		modelAndView.addObject("userName", user.getName());
		modelAndView.addObject("userSurname", user.getLastName());
		modelAndView.addObject("numberOfCourses", courseList.size());
		modelAndView.addObject("numberOfTeachers", teachersList.size());
		modelAndView.addObject("numberOfClasses", classesList.size());
		modelAndView.addObject("numberOfStudent", studentsList.size());

		return modelAndView;
	}

	@RequestMapping(value = "/search{course}{classes}{user}{day}", method = RequestMethod.GET)
	public ModelAndView timetablePageSearch(ModelAndView modelAndView, @RequestParam("day") String day,
			@RequestParam("class") String classes, @RequestParam(value = "course", required = false) String course,
			@RequestParam("user") String user) {

		modelAndView.setViewName("/admin/timetable");
		List<TimeTable> timetable = timeTableService
				.findByCourse_CourseNameLikeAndClasses_ClassNameLikeAndUser_NameLikeAndDayLike(course, classes, user,
						day);
		modelAndView.addObject("timetableList", timetable);
		System.out.println(timetable);
		return modelAndView;
	}

	@RequestMapping(value = "/studentSearch{name}{lastName}", method = RequestMethod.GET)
	public ModelAndView studentPageSearch(ModelAndView modelAndView,
			@RequestParam(value = "name", required = false) String name, @RequestParam("lastName") String lastName) {

		System.out.println(name);
		System.out.println(lastName);
		modelAndView.setViewName("/admin/students");

		List<User> students = userService.findByRoles_RoleAndNameLikeAndLastNameLike("SITE_STUDENT", name, lastName);
		System.out.println(students);
		modelAndView.addObject("listOfStudents", students);
		return modelAndView;
	}

	@RequestMapping(value = "/parentSearch{name}{lastName}", method = RequestMethod.GET)
	public ModelAndView parentPageSearch(ModelAndView modelAndView,
			@RequestParam(value = "name", required = false) String name, @RequestParam("lastName") String lastName) {

		modelAndView.setViewName("/admin/parents");

		List<User> parents = userService.findByRoles_RoleAndNameLikeAndLastNameLike("SITE_PARENT", name, lastName);
		modelAndView.addObject("listOfParents", parents);
		return modelAndView;
	}

	@RequestMapping(value = "/teacherSearch{name}{lastName}", method = RequestMethod.GET)
	public ModelAndView teacherPageSearch(ModelAndView modelAndView,
			@RequestParam(value = "name", required = false) String name, @RequestParam("lastName") String lastName) {

		modelAndView.setViewName("/admin/teachers");

		List<User> parents = userService.findByRoles_RoleAndNameLikeAndLastNameLike("SITE_TEACHER", name, lastName);
		modelAndView.addObject("listOfTeachers", parents);

		return modelAndView;
	}

	@RequestMapping(value = "/courseSearch{name}", method = RequestMethod.GET)
	public ModelAndView coursePageSearch(ModelAndView modelAndView,
			@RequestParam(value = "name", required = false) String name) {

		modelAndView.setViewName("/admin/courses");

		List<Course> courses = courseService.findByCourseNameLike(name);
		modelAndView.addObject("coursesList", courses);
		return modelAndView;
	}

	@RequestMapping(value = "/classesSearch{name}", method = RequestMethod.GET)
	public ModelAndView classesPageSearch(ModelAndView modelAndView,
			@RequestParam(value = "name", required = false) String name) {
		System.out.println(name);
		modelAndView.setViewName("/admin/classes");

		List<Classes> classes = classesService.findByClassNameLike(name);
		System.out.println(classes);
		modelAndView.addObject("listOfClasses", classes);
		return modelAndView;
	}

	@RequestMapping(value = "/teachers")
	public ModelAndView teachersPage(ModelAndView modelAndView) {

		modelAndView = new ModelAndView("/admin/teachers");
		Role studentRole = roleService.findByRole("SITE_TEACHER");
		List<User> studentsList = userService.findByRoles(studentRole);
		modelAndView.addObject("listOfTeachers", studentsList);
		return modelAndView;
	}

	@RequestMapping(value = "/timeTable")
	public ModelAndView timetablePage(ModelAndView modelAndView) {

		modelAndView = new ModelAndView("/admin/timetable");
		List<TimeTable> timeTable = timeTableService.findAll();
		modelAndView.addObject("timetableList", timeTable);

		return modelAndView;
	}

	@RequestMapping(value = "/classes")
	public ModelAndView classesPage(ModelAndView modelAndView) {

		modelAndView = new ModelAndView("/admin/classes");
		List<Classes> classes = classesService.findByStatus("0");
		modelAndView.addObject("listOfClasses", classes);

		return modelAndView;
	}

	@RequestMapping(value = "/parents")
	public ModelAndView parentsPage(ModelAndView modelAndView) {

		modelAndView = new ModelAndView("/admin/parents");
		Role studentRole = roleService.findByRole("SITE_PARENT");
		List<User> parentsList = userService.findByRoles(studentRole);
		modelAndView.addObject("listOfParents", parentsList);
		return modelAndView;
	}

	@RequestMapping(value = "/students")
	public ModelAndView studentsPage(ModelAndView modelAndView) {

		modelAndView = new ModelAndView("/admin/students");
		Role studentRole = roleService.findByRole("SITE_STUDENT");
		List<User> studentsList = userService.findByRoles(studentRole);
		modelAndView.addObject("listOfStudents", studentsList);
		return modelAndView;
	}

	@RequestMapping(value = "/courses")
	public ModelAndView coursesPage(ModelAndView modelAndView) {

		modelAndView = new ModelAndView("/admin/courses");
		List<Course> coursesList = courseService.ListAllCourses();
		modelAndView.addObject("coursesList", coursesList);

		return modelAndView;
	}

	@RequestMapping(value = "/addClass")
	public ModelAndView addClassesPage(ModelAndView modelAndView) {

		Role teacherRole = roleService.findByRole("SITE_TEACHER");
		List<User> teachersList = userService.findByRoles(teacherRole);
		Classes classes = new Classes();

		modelAndView = new ModelAndView("/admin/addClasses");
		modelAndView.addObject("classes", classes);
		modelAndView.addObject("teachersList", teachersList);

		return modelAndView;
	}

	@RequestMapping(value = "/addStudents")
	public ModelAndView addStudentPage(ModelAndView modelAndView) {

		List<Classes> classes = classesService.findByStatus("0");
		User studentUser = new User();

		modelAndView = new ModelAndView("/admin/addStudent");
		modelAndView.addObject("students", studentUser);
		modelAndView.addObject("classesList", classes);

		return modelAndView;
	}

	@RequestMapping(value = "/addCourse")
	public ModelAndView addCoursePage(ModelAndView modelAndView) {

		Role studentRole = roleService.findByRole("SITE_TEACHER");
		List<User> teachersList = userService.findByRoles(studentRole);
		List<Classes> classesList = classesService.findByStatus("0");

		Course course = new Course();

		modelAndView = new ModelAndView("/admin/addCourse");
		modelAndView.addObject("course", course);
		modelAndView.addObject("teachersList", teachersList);
		modelAndView.addObject("classesList", classesList);
		return modelAndView;
	}

	@RequestMapping(value = "/addTimetable")
	public ModelAndView addTimeTablePage(ModelAndView modelAndView) {

		Role teacherRole = roleService.findByRole("SITE_TEACHER");
		List<User> teachersList = userService.findByRoles(teacherRole);

		List<Classes> classesList = classesService.findByStatus("0");
		List<Course> coursesList = courseService.ListAllCourses();
		TimeTable timetable = new TimeTable();
		modelAndView = new ModelAndView("/admin/addTimetable");
		modelAndView.addObject("timetable", timetable);
		modelAndView.addObject("teachersList", teachersList);
		modelAndView.addObject("classesList", classesList);
		modelAndView.addObject("coursesList", coursesList);

		return modelAndView;
	}

	@RequestMapping(value = "/addParent")
	public ModelAndView addParentPage(ModelAndView modelAndView) {

		User parent = new User();
		Role studentRole = roleService.findByRole("SITE_STUDENT");
		List<User> studentList = userService.findByRoles(studentRole);
		modelAndView = new ModelAndView("/admin/addParent");
		modelAndView.addObject("parents", parent);
		modelAndView.addObject("students", studentList);
		return modelAndView;
	}

	@RequestMapping(value = "/addTeacher")
	public ModelAndView addTeacherPage(ModelAndView modelAndView) {

		User teacher = new User();
		modelAndView = new ModelAndView("/admin/addTeacher");
		modelAndView.addObject("teacher", teacher);

		return modelAndView;
	}

//////////////////////////////////////////////////////////////////////////

	@RequestMapping(value = "/addStudents", method = RequestMethod.POST)
	public String registerStudent(@Valid User user, Model modelAndView, BindingResult bindingResult, ModelMap modelMap,
			RedirectAttributes atts) throws IOException {
		modelAndView.addAttribute("users", new User());
//		 Check for the validations
		if (userService.isUserAlreadyPresent(user)) {
			modelAndView.addAttribute("successMessage", "Student  is already registered!");
		}
		// we will save the user if, no binding errors
		else {
			// Create Token and Save
			modelAndView.addAttribute("successMessage", "Student is registered successfully!");

			user.setResetToken(UUID.randomUUID().toString().substring(0, 8));
			Role studentRole = roleService.findByRole("SITE_STUDENT");
			user.setRoles(new HashSet<Role>(Arrays.asList(studentRole)));
			user.setPassword(encoder.encode(user.getPassword()));
			userService.save(user);

		}
		return "redirect:/addStudents";
	}

	@RequestMapping(value = "/addClass", method = RequestMethod.POST)
	public ModelAndView registerClass(@Valid Classes classes, BindingResult bindingResult, ModelMap modelMap,
			RedirectAttributes atts) throws IOException {
		ModelAndView modelAndView = new ModelAndView();
		// Check for the validations
		if (bindingResult.hasErrors()) {
			return modelAndView;

		}

		// we will save the user if, no binding errors
		else {
			// Create Token and Save

			classes.setStatus("0");
			classesService.Save(classes);

		}
		modelAndView.addObject("classes", new Classes());
		modelAndView.setViewName("/admin/addClasses");
		return modelAndView;
	}

	@RequestMapping(value = "/addTeacher", method = RequestMethod.POST)
	public ModelAndView registerTeacher(@Valid User teacher, BindingResult bindingResult, ModelMap modelMap,
			RedirectAttributes atts) throws IOException {
		ModelAndView modelAndView = new ModelAndView();
		atts.addAttribute("name", teacher.getName());
		// Check for the validations
		if (bindingResult.hasErrors()) {
			return modelAndView;

		} else if (userService.isUserAlreadyPresent(teacher)) {
			modelAndView.addObject("successMessage", "Teacher  is already registered!");
		}
		// we will save the user if, no binding errors
		else {
			// Create Token and Save
			modelAndView.addObject("successMessage", "Teacher is registered successfully!");

			teacher.setResetToken(UUID.randomUUID().toString().substring(0, 8));
			Role studentRole = roleService.findByRole("SITE_TEACHER");
			teacher.setRoles(new HashSet<Role>(Arrays.asList(studentRole)));
			teacher.setPassword(encoder.encode(teacher.getPassword()));
			userService.save(teacher);

		}
		modelAndView.addObject("teacher", new User());
		modelAndView.setViewName("/admin/addTeacher");
		return modelAndView;
	}

	@RequestMapping(value = "/addCourse", method = RequestMethod.POST)
	public ModelAndView registerCourse(@Valid Course course, BindingResult bindingResult, ModelMap modelMap,
//			@RequestParam("classes") List<String> checkClasses,
			RedirectAttributes atts) throws IOException {
		ModelAndView modelAndView = new ModelAndView();
		atts.addAttribute("name", course.getCourseName());
		// Check for the validations
		if (bindingResult.hasErrors()) {
			return modelAndView;

		}
		// we will save the user if, no binding errors
		else {
			// Create Token and Save
			modelAndView.addObject("successMessage", "Course is registered successfully!");
//			List<Classes> classes=classesService.findAllById(checkClasses);
//			course.addCategory(classes);
			courseService.save(course);
		}
		modelAndView.addObject("course", new Course());
		modelAndView.setViewName("/admin/addCourse");
		return modelAndView;
	}

	@RequestMapping(value = "/addParent", method = RequestMethod.POST)
	public ModelAndView registerParent(@Valid User parent, BindingResult bindingResult, ModelMap modelMap,
			RedirectAttributes atts) throws IOException {
		ModelAndView modelAndView = new ModelAndView();
		atts.addAttribute("name", parent.getName());
		// Check for the validations
		if (bindingResult.hasErrors()) {
			return modelAndView;

		} else if (userService.isUserAlreadyPresent(parent)) {
			modelAndView.addObject("successMessage", "Parent  is already registered!");
		}
		// we will save the user if, no binding errors
		else {
			// Create Token and Save
			modelAndView.addObject("successMessage", "Parent is registered successfully!");
			parent.setResetToken(UUID.randomUUID().toString().substring(0, 8));
			Role parentRole = roleService.findByRole("SITE_PARENT");
			parent.setRoles(new HashSet<Role>(Arrays.asList(parentRole)));
			parent.setPassword(encoder.encode(parent.getPassword()));
			userService.save(parent);

		}
		modelAndView.addObject("parents", new User());
		modelAndView.setViewName("/admin/addParent");
		return modelAndView;
	}

	@RequestMapping(value = "/addTimetable", method = RequestMethod.POST)
	public ModelAndView registerTimetable(@Valid TimeTable timeTable, BindingResult bindingResult, ModelMap modelMap,
			RedirectAttributes atts) throws IOException {
		ModelAndView modelAndView = new ModelAndView();
		atts.addAttribute("name", timeTable.getTableName());
		// Check for the validations
		if (bindingResult.hasErrors()) {
			return modelAndView;

		}
		// we will save the user if, no binding errors
		else {
			// Create Token and Save
			modelAndView.addObject("successMessage", "Timetable is registered successfully!");
			timeTableService.save(timeTable);

		}
		modelAndView.addObject("timetable", new TimeTable());
		modelAndView.setViewName("/admin/addTimetable");
		return modelAndView;
	}

	@RequestMapping(value = "/editStudent/{userId}")
	public ModelAndView editStudents(ModelAndView modelAndView, @PathVariable("userId") Integer userId) {

		modelAndView.setViewName("admin/editStudent");
		List<Classes> classesList = classesService.findByStatus("0");

		User user = userService.getOne(userId);
		Classes classes = classesService.findByUser(user);
		System.out.println(user.getEmail());
		modelAndView.addObject("classesName", classes);
		modelAndView.addObject("classesList", classesList);
		modelAndView.addObject("student", user);
		modelAndView.addObject("studentId", user.getUserId());
		modelAndView.addObject("studentEmail", user.getEmail());
		modelAndView.addObject("studentName", user.getName());
		modelAndView.addObject("studentSurname", user.getLastName());
		modelAndView.addObject("studentAddress", user.getAddress());
		modelAndView.addObject("studentAddmissionYear", user.getAddmissionYear());
		modelAndView.addObject("studentBirthdate", user.getBirthdate());
		modelAndView.addObject("studentGender", user.getGender());
		modelAndView.addObject("studentNationality", user.getNationality());
		modelAndView.addObject("studentClass", user.getClasses());

		return modelAndView;
	}

	@RequestMapping(value = "/editStudent/{userId}", method = RequestMethod.POST)
	public String updateStudent(@Valid User user, BindingResult bindingResult, ModelMap modelMap,
			@RequestParam("name") String name, @RequestParam("lastName") String lastName,
			@RequestParam("update") Integer studentId, @RequestParam("email") String email,
			@RequestParam("address") String address, @RequestParam("addmissionYear") String addmissionYear,
			@RequestParam("birthdate") String birthdate, @RequestParam("gender") String gender,
			@RequestParam("nationality") String nationality, @RequestParam("class") String classes) {

		user = userService.getOne(studentId);
		System.out.println(user.getName());
		// Check for the validations
		System.out.println(classes);
		Classes classses = classesService.getOne(classes);
		System.out.println(classses.getClassName());

		user = userService.getOne(studentId);
		user.setName(name);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setAddmissionYear(addmissionYear);
		user.setGender(gender);
		user.setNationality(nationality);
		user.setClasses(classses);
		user.setBirthdate(birthdate);
		user.setClasses(classses);

		userService.save(user);

		return "redirect:/editStudent/" + studentId;
	}

	@RequestMapping(value = "/editTeacher/{userId}")
	public ModelAndView editTeacher(ModelAndView modelAndView, @PathVariable("userId") Integer userId) {

		modelAndView.setViewName("admin/editTeacher");
		User user = userService.getOne(userId);
		modelAndView.addObject("teacher", user);
		modelAndView.addObject("teacherId", user.getUserId());
		modelAndView.addObject("teacherEmail", user.getEmail());
		modelAndView.addObject("teacherName", user.getName());
		modelAndView.addObject("teacherSurname", user.getLastName());
		modelAndView.addObject("teacherAddress", user.getAddress());
		modelAndView.addObject("teacherTitle", user.getTitle());
		modelAndView.addObject("teacherBirthdate", user.getBirthdate());
		modelAndView.addObject("teacherGender", user.getGender());
		modelAndView.addObject("teacherNationality", user.getNationality());

		return modelAndView;
	}

	@RequestMapping(value = "/editTeacher/{userId}", method = RequestMethod.POST)
	public String updateTeacher(@Valid User user, BindingResult bindingResult, ModelMap modelMap,
			@RequestParam("name") String name, @RequestParam("lastName") String lastName,
			@RequestParam("update") Integer teacherId, @RequestParam("email") String email,
			@RequestParam("address") String address, @RequestParam("birthdate") String birthdate,
			@RequestParam("gender") String gender, @RequestParam("nationality") String nationality,
			@RequestParam("title") String title) {
		user = userService.getOne(teacherId);
		System.out.println(user.getName());
		// Check for the validations

		user = userService.getOne(teacherId);
		user.setName(name);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setAddress(address);
		user.setGender(gender);
		user.setNationality(nationality);
		user.setBirthdate(birthdate);
		userService.save(user);

		return "redirect:/editTeacher/" + teacherId;
	}

	@RequestMapping(value = "/editParent/{userId}")
	public ModelAndView editParent(ModelAndView modelAndView, BindingResult bindingResult,
			@PathVariable("userId") Integer userId) {

		modelAndView.setViewName("admin/editParent");
		User user = userService.getOne(userId);
		System.out.println(user);
		Role studentRole = roleService.findByRole("SITE_STUDENT");
		List<User> studentsList = userService.findByRoles(studentRole);

		System.out.println(user.getEmail());
		modelAndView.addObject("parent", user);
		modelAndView.addObject("studentsList", studentsList);
		modelAndView.addObject("parentId", user.getUserId());
		modelAndView.addObject("parentEmail", user.getEmail());
		modelAndView.addObject("parentName", user.getName());
		modelAndView.addObject("parentSurname", user.getLastName());
		modelAndView.addObject("parentAddress", user.getAddress());
		modelAndView.addObject("parentGender", user.getGender());
		modelAndView.addObject("parentNationality", user.getNationality());
		modelAndView.addObject("parentOfStudent", user.getParentOfStudent());

		return modelAndView;
	}

	@RequestMapping(value = "/editParent/{userId}", method = RequestMethod.POST)
	public String updateParent(@Valid User user, BindingResult bindingResult, ModelMap modelMap,
			@RequestParam("name") String name, @RequestParam("lastName") String lastName,
			@RequestParam("update") Integer parentId, @RequestParam("email") String email,
			@RequestParam("address") String address, @RequestParam("gender") String gender,
			@RequestParam("nationality") String nationality, @RequestParam("student") Integer student) {

		user = userService.getOne(parentId);
		System.out.println(user.getName());
		System.out.println(student);
		// Check for the validations

		User studentUpdate = userService.getOne(student);
		user = userService.getOne(parentId);
		user.setName(name);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setGender(gender);
		user.setNationality(nationality);
		user.setParentOfStudent(studentUpdate);
		userService.save(user);

		return "redirect:/editParent/" + parentId;
	}

	@RequestMapping(value = "/editTimetable/{id}")
	public ModelAndView editTimetable(ModelAndView modelAndView, @PathVariable("id") int timetableId) {

		Role studentRole = roleService.findByRole("SITE_TEACHER");
		List<User> teachersList = userService.findByRoles(studentRole);
		TimeTable timetable = timeTableService.getOne(timetableId);
		List<Classes> classes = classesService.findByStatus("0");
		List<Course> course = courseService.findAll();
		modelAndView.setViewName("admin/editTimetable");

		modelAndView.addObject("teachers", teachersList);
		modelAndView.addObject("courseList", course);
		modelAndView.addObject("timetableId", timetable.getId());
		modelAndView.addObject("classesList", classes);
		modelAndView.addObject("name", timetable.getTableName());
		modelAndView.addObject("day", timetable.getDay());
		modelAndView.addObject("end", timetable.getEnd());
		modelAndView.addObject("start", timetable.getStart());
		modelAndView.addObject("classes", timetable.getClasses().getClassId());
		modelAndView.addObject("course", timetable.getCourse().getCourseId());
		modelAndView.addObject("teacherTimetable", timetable.getUser().getUserId());
		modelAndView.addObject("teacherNameClass", timetable.getUser().getName());
		modelAndView.addObject("teacherSurnameClass", timetable.getUser().getLastName());

		return modelAndView;
	}

	@RequestMapping(value = "/editTimetable/{id}", method = RequestMethod.POST)
	public String updateTimetable(@Valid TimeTable timetable, BindingResult bindingResult, ModelMap modelMap,
			@RequestParam("name") String tableName, @RequestParam("teacher") Integer teacherId,
			@RequestParam("update") Integer timetableId, @RequestParam("day") String day,
			@RequestParam("end") String end, @RequestParam("start") String start, @RequestParam("class") String classId,
			@RequestParam("course") String courseId) {

		timetable = timeTableService.getOne(timetableId);
		User user = userService.getOne(teacherId);
		Classes classes = classesService.getOne(classId);
		Course course = courseService.getOne(courseId);
		timetable.setClasses(classes);
		timetable.setCourse(course);
		timetable.setDay(day);
		timetable.setUser(user);
		timetable.setTableName(tableName);
		timetable.setEnd(end);
		timetable.setStart(start);
		timeTableService.save(timetable);

		return "redirect:/editTimetable/" + timetableId;
	}

	@RequestMapping(value = "/editClasses/{classId}")
	public ModelAndView editClasses(ModelAndView modelAndView, @PathVariable("classId") String classId) {

		Role studentRole = roleService.findByRole("SITE_TEACHER");
		List<User> teachersList = userService.findByRoles(studentRole);
		Classes classes = classesService.getOne(classId);
		modelAndView.setViewName("admin/editClasses");

		modelAndView.addObject("teachers", teachersList);
		modelAndView.addObject("className", classes.getClassName());
		modelAndView.addObject("classes", classes);
		modelAndView.addObject("classesId", classes.getClassId());
		modelAndView.addObject("teacherClass", classes.getUser().getUserId());
		modelAndView.addObject("teacherNameClass", classes.getUser().getName());
		modelAndView.addObject("teacherSurnameClass", classes.getUser().getLastName());

		return modelAndView;
	}

	@RequestMapping(value = "/editClasses/{classId}", method = RequestMethod.POST)
	public String updateClasses(@Valid Classes classes, BindingResult bindingResult, ModelMap modelMap,
			@RequestParam("name") String className, @RequestParam("teacher") Integer teacherId,
			@RequestParam("update") String classId) {

		if (bindingResult.hasErrors()) {
			return "redirect:/editClasses/" + classId;
		} else {

			System.out.println(teacherId);
			User user = userService.getOne(teacherId);
			classes = classesService.getOne(classId);
			classes.setClassName(className);
			classes.setUser(user);
			user.setClasses(classes);
			userService.save(user);
			classesService.save(classes);
		}
		return "redirect:/editClasses/" + classId;
	}

	@RequestMapping(value = "/editCourse/{courseId}", method = RequestMethod.POST)
	public String updateCourse(@Valid Course course, BindingResult bindingResult, ModelMap modelMap,
			@RequestParam("name") String courseName, @RequestParam("credits") String credits,
			@RequestParam("teacher") Integer teacherId,
			@RequestParam(value = "class", required = false) List<String> classId,
			@RequestParam("update") String courseId) {
		System.out.println(teacherId);
		if (bindingResult.hasErrors()) {

			return "redirect:/editCourse/" + courseId;
		} else if (classId == null) {
			return "redirect:/editCourse/" + courseId;
		} else {
			List<Classes> classes = classesService.findAllById(classId);
//			List<Classes> classesCourse = courseService.findByClasses_ClassId(courseId);
			System.out.println(classId);
			User user = userService.getOne(teacherId);
			course = courseService.getOne(courseId);
			course.setCourseName(courseName);
			course.setCourseCredit(credits);
			course.setUser(user);
//			course.setClasses(classesCourse);
			course.addClassesList(classes);
			courseService.save(course);
		}
		return "redirect:/editCourse/" + courseId;
	}

	@RequestMapping(value = "/editCourse/{courseId}")
	public ModelAndView editCourse(ModelAndView modelAndView, @PathVariable("courseId") String courseId) {

		Role studentRole = roleService.findByRole("SITE_TEACHER");
		List<User> teachersList = userService.findByRoles(studentRole);
		Course course = courseService.getOne(courseId);

		List<Classes> classesList = classesService.findByStatus("0");
		System.out.println(course.getClasses().stream().map(Classes::getClassId).collect(Collectors.toList()));
		modelAndView.setViewName("admin/editCourses");
		modelAndView.addObject("teachers", teachersList);
		modelAndView.addObject("teacherClass", course.getUser().getUserId());
		modelAndView.addObject("courseName", course.getCourseName());
		modelAndView.addObject("courseCredit", course.getCourseCredit());
		modelAndView.addObject("course", course);
		modelAndView.addObject("credits", course.getCourseCredit());
		modelAndView.addObject("courseId", course.getCourseId());
		modelAndView.addObject("teacherNameClass", course.getUser().getName());
		modelAndView.addObject("teacherSurnameClass", course.getUser().getLastName());
		modelAndView.addObject("classesCourse",
				course.getClasses().stream().map(Classes::getClassId).collect(Collectors.toList()));
		modelAndView.addObject("classesList", classesList);
		return modelAndView;
	}

	/////////////////////////////////////////////////////////////////////////////////////////////
	@RequestMapping(value = "/deleteStudent/{userId}")
	public String deleteStudent(Model model, User user, @PathVariable("userId") Integer userId) {

		user = userService.getOne(userId);
		User userParent = userService.findByParentOfStudent(user);
		userService.delete(userParent);
		userService.delete(user);
		return "redirect:/students";
	}

	@RequestMapping(value = "/deleteClasses/{classId}")
	public String deleteClass(Model model, @PathVariable("classId") String classId) {

		Classes classes = classesService.getOne(classId);
		Role teacherRole = roleService.findByRole("SITE_STUDENT");
		List<User> studentsList = userService.findByRoles(teacherRole);
		List<Course> course = courseService.findByClasses_ClassId(classId);
		List<Attendance> attendance = attendanceRepository.findByClasses(classes);
		List<Marks> marks = markService.findByClasses(classes);
		List<TimeTable> timetable = timeTableService.findByClasses(classes);
		Iterator<Attendance> attendnanceIter = attendance.iterator();
		Iterator<User> studentIter = studentsList.iterator();

		System.out.println(course.stream().map(Course::getCourseName).collect(Collectors.toList()));

		classes.remove();
		classes.setStatus("1");
		classesService.save(classes);
		timeTableService.deleteAll(timetable);
		markService.deleteAll(marks);

		while (studentIter.hasNext()) {
			User students = studentIter.next();
			if (students.getClasses() != null) {
				students = userService.findByClasses(classes);
				students.setClasses(null);
				userService.save(students);
			} else {
				return "redirect:/classes";
			}
		}
		while (attendnanceIter.hasNext()) {
			Attendance attendances = attendnanceIter.next();
			attendances.setCourse(null);
			attendances.setClasses(null);
			attendanceRepository.save(attendances);
		}

		return "redirect:/classes";
	}

	@RequestMapping(value = "/deleteCourse/{courseId}")
	public String deleteCourse(Model model, User user, @PathVariable("courseId") String courseId) {

		Course course = courseService.getOne(courseId);
		List<TimeTable> timetable = timeTableService.findByCourse(course);
		timeTableService.deleteAll(timetable);
		courseService.delete(course);
		return "redirect:/courses";
	}

	@RequestMapping(value = "/deleteTimetable/{id}")
	public String deleteTimetable(Model model, User user, @PathVariable("id") int id) {

		TimeTable timetable = timeTableService.getOne(id);
		timeTableService.delete(timetable);
		return "redirect:/timeTable";
	}

}
