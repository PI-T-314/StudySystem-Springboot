package com.system.demo.date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DateService {
	
	@Autowired
	DateRepository dr;

	public void save(Date date) {
		dr.save(date);
	}
	
//	public List<Date> getDates(List<Integer> courseIds){
//		return dr.findCoursesWithDatesByIds(courseIds);
//	}
}
