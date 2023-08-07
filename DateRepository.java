package com.system.demo.date;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.system.demo.course.Course;

public interface DateRepository extends JpaRepository<Date, Integer>{
	

	@Query("SELECT c FROM Course c JOIN FETCH c.dates WHERE c.id IN :courseIds")
    List<Course> findCoursesWithDatesByIds(List<Integer> courseIds);

}
