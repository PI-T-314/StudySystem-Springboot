package com.system.demo.user;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.demo.course.Course;

@Service
public class UsersService {
	
	@Autowired
	UsersRepository ur;
	
	
//	public void save() {
//		Users user = new Users(1, "Ali", "Zein", "az@gmail.com", "123", 1);
//		ur.save(user);
//	}
	
	public List<Users> json(){
		return ur.findAll();
	}

	public Users checkAndGet(String email, String password) {
		return ur.findByEmailAndPassword(email, password);
	}
	
	public List<Course> getCourses(int id) {
		List<Course> c = ur.findByUserId(id);
		return c;
	}
	
	public List<Course> getCoursesAndDates(int userId) {
		return ur.findCoursesWithDatesByUserId(userId);
	}
	
	public List<Course> getTodayCourses(int userId){
		LocalDate today = LocalDate.now();
		return ur.findTodayCourses(userId, today);
	}
	
	public List<Course> getTomorrowCourses(int userId){
		LocalDate tmrw = LocalDate.now().plusDays(1);
		return ur.findTomorrowCourses(userId, tmrw);
	}
	
	public List<Course> getYesterdayCourses(int userId){
		LocalDate yest = LocalDate.now().minusDays(1);
		return ur.findYesterdayCourses(userId, yest);
	}
	
	public List<Course> getCompletedCourses(int userId){
		LocalDate today = LocalDate.now();
		return ur.findCompletedCourses(userId, today);
	}
	
	public List<Course> getUpcomingCourses(int userId){
		LocalDate today = LocalDate.now();
		return ur.findUpcomingCourses(userId, today);
	}

	public void dropCourse(int userId, int courseId) {
		System.out.println(userId + "  " + courseId);
		ur.deleteCourse(userId, courseId);
		
	}

	public Users getUser(int userId) {
		return ur.findById(userId).get();
	}

	public List<Object[]> getUserAndCourseNumbers() {
		return ur.findNumberOfCoursesPerUser();
	}

	public void save(Users user) {
		ur.save(user);
	}
	
	
	
	
	
}
