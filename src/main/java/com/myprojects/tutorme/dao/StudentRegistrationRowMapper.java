package com.myprojects.tutorme.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.myprojects.tutorme.model.StudentRegistration;

public class StudentRegistrationRowMapper implements RowMapper<StudentRegistration>{
	public StudentRegistration mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		// TODO Auto-generated method stub			
		StudentRegistration dbCourse = new StudentRegistration();
		dbCourse.setCourseID(resultSet.getString(1));
		dbCourse.setEmailID(resultSet.getString(2));
		dbCourse.setGrades(resultSet.getString(3));
		dbCourse.setCompleted(resultSet.getString(4));
		return dbCourse;	
	}
}