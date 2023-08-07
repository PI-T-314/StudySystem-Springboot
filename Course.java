package com.system.demo.course;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.system.demo.date.Date;
import com.system.demo.user.Users;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Course")
public class Course {
	
	@Id
	private int courseId;
	private String courseName;
	
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "users_course",
				joinColumns = @JoinColumn(name="courseId"),
				inverseJoinColumns = @JoinColumn(name = "usersId")
			)
	private List<Users> users = new ArrayList<>();
	
	
	@OneToMany(targetEntity = Date.class, mappedBy = "course")
	@JsonManagedReference
	private List<Date> dates = new ArrayList<>();
	
	
	public Course(int courseId, String courseName, Users users, List<Date> dates) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.dates = dates;
	}

	public Course() {
		super();
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public List<Users> getUser() {
		return users;
	}

	
	public List<Date> getDates() {
		return dates;
	}


	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", courseName=" + courseName + ", user=" + users + ", dates=" + dates
				+ "]";
	}
	
	

}
