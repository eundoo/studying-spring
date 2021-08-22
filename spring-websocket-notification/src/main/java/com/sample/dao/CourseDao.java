package com.sample.dao;

import java.util.List;

import com.sample.vo.Course;

public interface CourseDao {

	List<Course> getAllCourses();
	Course getCourseByNo(int no);
	void updateCourse(Course course);
}
