package com.system.demo.course;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CourseRepository extends JpaRepository<Course, Integer>{
	
//	@Query("SELECT c FROM Course c JOIN c.dates d JOIN c.users u WHERE u.id != :userId and d.day > :tdy")
//	List<Course> findAllUnregisteredCourses(int userId, LocalDate tdy);
	
	@Query("SELECT c FROM Course c JOIN c.dates d WHERE d.day >= :tdy AND c NOT IN (SELECT cu FROM Course cu JOIN cu.users u WHERE u.id = :userId)")
	List<Course> findAllUnregisteredCourses(int userId, LocalDate tdy);
	
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO users_course(users_id, course_id) values(:userId, :courseId)", nativeQuery = true)
	void addCourse(int userId, int courseId);

}
