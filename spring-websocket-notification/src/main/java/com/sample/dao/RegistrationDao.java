package com.sample.dao;

import java.util.List;

import com.sample.vo.Registration;

public interface RegistrationDao {

	void insertRegistration(Registration registration);
	void deleteRegistration(int no);
	Registration getRegistrationByNo(int no);
	List<Registration> getRegisteredCoursesByUserNo(int userNo);
}
