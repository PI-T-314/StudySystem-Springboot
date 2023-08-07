package com.system.demo.user;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.system.demo.course.Course;

public interface UsersRepository extends JpaRepository<Users, Integer>{
	
	public Users findByEmailAndPassword(String email, String password);
	
	@Query("SELECT c FROM Course c JOIN c.users u WHERE u.id = :userId")
	List<Course> findByUserId(int userId);
	
	
	@Query("SELECT c FROM Course c JOIN c.dates d JOIN c.users u WHERE u.id = :userId")
    List<Course> findCoursesWithDatesByUserId(int userId);
	
	@Query("SELECT c FROM Course c JOIN c.dates d JOIN c.users u WHERE u.id = :userId and d.day = :today")
	List<Course> findTodayCourses(int userId, LocalDate today);
	
	@Query("SELECT c FROM Course c JOIN c.dates d JOIN c.users u WHERE u.id = :userId and d.day = :tmrw")
	List<Course> findTomorrowCourses(int userId, LocalDate tmrw);
	
	@Query("SELECT c FROM Course c JOIN c.dates d JOIN c.users u WHERE u.id = :userId and d.day = :yest")
	List<Course> findYesterdayCourses(int userId, LocalDate yest);
	
	@Query("SELECT c FROM Course c JOIN c.dates d JOIN c.users u WHERE u.id = :userId and d.day < :today")
	List<Course> findCompletedCourses(int userId, LocalDate today);
	
	@Query("SELECT c FROM Course c JOIN c.dates d JOIN c.users u WHERE u.id = :userId and d.day > :today")
	List<Course> findUpcomingCourses(int userId, LocalDate today);
	
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM users_course WHERE users_id = :userId AND course_id = :courseId", nativeQuery = true)
	void deleteCourse( int userId, int courseId);
	
	@Query("SELECT u.id, u.firstName, u.lastName, COUNT(c.courseId) " +
		       "FROM Users u LEFT JOIN u.courses c WHERE u.type = 2 GROUP BY u.id, u.firstName, u.lastName")
	List<Object[]> findNumberOfCoursesPerUser();


	
	
	
	
	
}
