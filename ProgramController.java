package com.system.demo;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.system.demo.course.Course;
import com.system.demo.course.CourseService;
import com.system.demo.date.Date;
import com.system.demo.date.DateService;
import com.system.demo.user.Users;
import com.system.demo.user.UsersService;

@Controller
public class ProgramController {
	
	@Autowired
	CourseService cs;
	@Autowired
	DateService ds;
	@Autowired
	UsersService us;
	
	@GetMapping("/")
	public String startup() {
		return "startup";
	}
	
	//Login page
	@GetMapping("/login")
	public String Login(Model model) {
		Users user = new Users();
		model.addAttribute("user", user);
		return "index";
	}
	
	//methods checks if user data is entered correctly, if true redirects user to their home page else refreshes the login page
	@PostMapping("/login")
	public String LoginCheck(@ModelAttribute("user") Users user, Model model, RedirectAttributes redirectAttributes) {
		Users check = us.checkAndGet(user.getEmail(), user.getPassword());
		
		if(check == null) {
			return "redirect:/";
		}else {
//			model.addAttribute("data", us.getUserAndCourseNumbers());
//			model.addAttribute("next", us.getUpcomingCourses(check.getUserId()));
//			model.addAttribute("com", us.getCompletedCourses(check.getUserId()));
//			model.addAttribute("yest", us.getYesterdayCourses(check.getUserId()));
//			model.addAttribute("tmrw", us.getTomorrowCourses(check.getUserId()));//			model.addAttribute("course", us.getCoursesAndDates(check.getUserId()));
//			model.addAttribute("check", check);
			redirectAttributes.addAttribute("check", check.getUserId());
			return "redirect:/mainPage";
		}
	}
	
	@GetMapping("/mainPage")
	public String mainPage(Model model, @RequestParam("check") int check) {
		model.addAttribute("data", us.getUserAndCourseNumbers());
		model.addAttribute("tdy", us.getTodayCourses(check));
		model.addAttribute("next", us.getUpcomingCourses(check));
		model.addAttribute("com", us.getCompletedCourses(check));
		model.addAttribute("yest", us.getYesterdayCourses(check));
		model.addAttribute("tmrw", us.getTomorrowCourses(check));
		model.addAttribute("course", us.getCoursesAndDates(check));
		model.addAttribute("check", us.getUser(check));
		return "mainPage";
	}
	
//	Json method to check retrived data
	@GetMapping("/test")
	@ResponseBody
	public List<Course> test() {
		Users check = us.checkAndGet("mj@example.com", "123");
		return us.getCoursesAndDates(check.getUserId());
	}
	
	//redirects to registration page for viewing, dropping, and adding courses
	@GetMapping("/register-courses")
	public String courseRegisration(@RequestParam("user") int userId, Model model) {
		model.addAttribute("user", us.getUser(userId));
		model.addAttribute("course", us.getCoursesAndDates(userId));
		model.addAttribute("unreg", cs.getUnregisteredCourses(userId));
		model.addAttribute("studentId", userId);
		return "registerCourse";
	}
	//registers selected course from the drop down menu
	@PostMapping("/register-courses")
	public String registerCourse(@RequestParam("userId") int userId ,@RequestParam("courseId") int courseId, RedirectAttributes redirectAttributes) {
		cs.registerCourse(userId, courseId);
		redirectAttributes.addAttribute("user", userId);
		return "redirect:/register-courses";
	}
	
	//drops selected courses
	@GetMapping("/drop")
	public String courseDrop(@RequestParam("courseId") int courseId, @RequestParam("userId") int userId, RedirectAttributes redirectAttributes) {
		us.dropCourse(userId, courseId);
		redirectAttributes.addAttribute("user", userId);
		return "redirect:/register-courses";
	}
	
	//goes to student register page for admins to register new users
	@GetMapping("/register-user")
	public String studentRegisterPage( @RequestParam("adminId") int adminId, Model model) {
		Users u = new Users();
		model.addAttribute("adminId", adminId);
		model.addAttribute("user", u);
		return "registerUser";
	}
	
	//completes registration and redirects to the main page
	@PostMapping("/register-complete")
	public String studentRegisterComplete(@ModelAttribute("user") Users user, @RequestParam("adminId") int adminId,RedirectAttributes redirectAttributes) {
		us.save(user);
		redirectAttributes.addAttribute("check", adminId);
		return "redirect:/mainPage";
	}
	
	//goes to the course adding page
	@GetMapping("/create-course")
	public String addCourse(@RequestParam("adminId") int adminId, Model model) {
		Course c = new Course();
		Date d = new Date();
		model.addAttribute("date", d);
		model.addAttribute("course", c);
		model.addAttribute("adminId", adminId);
		return "courseAdd.html";
	}
	
	//adds the course with its respective date to the database
	@PostMapping("/courseAdd-Complete")
	public String addCourseComplete(@ModelAttribute("course") Course course, @ModelAttribute("date") Date date, @RequestParam("adminId") int adminId, RedirectAttributes redirectAttributes) {
		date.setCourse(course);
		ds.save(date);
		cs.save(course);
		redirectAttributes.addAttribute("check", adminId);
		return "redirect:/mainPage";
	}
	
	
	
	
	
	
	
	
	
	
	
}
