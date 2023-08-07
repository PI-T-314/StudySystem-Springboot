package com.system.demo.course;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
	
	@Autowired
	CourseRepository cr;

	public List<Course> getUnregisteredCourses(int userId){
		LocalDate tdy = LocalDate.now();
		return cr.findAllUnregisteredCourses(userId, tdy);
	}

	public void registerCourse(int userId, int courseId) {
		cr.addCourse(userId, courseId);
	}

	public void save(Course course) {
		cr.save(course);
	}
	
	
}
