package com.system.demo.date;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.system.demo.course.Course;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Date")
public class Date {
	
	@Id
	private int dateId;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate day;
	private LocalTime time;
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "courseId", referencedColumnName = "courseId")
	@JsonBackReference
	private Course course;
	
	
	

	public Date(int dateId, Course course) {
		super();
		this.dateId = dateId;
		this.course = course;
	}

	public Date() {
		super();
	}

	public int getDateId() {
		return dateId;
	}

	public void setDateId(int dateId) {
		this.dateId = dateId;
	}

	public LocalDate getDay() {
		return day;
	}

	public void setDay(LocalDate day) {
		this.day = day;

	}

	

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	@Override
	public String toString() {
		return "Date [dateId=" + dateId + ", day=" + day + ", time=" + time + ", course="
				+ course + "]";
	}
	
	
	
}
