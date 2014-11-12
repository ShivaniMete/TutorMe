package com.myprojects.tutorme.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.myprojects.tutorme.model.Course;

public class CourseRowMapper implements RowMapper<Course>{

	//@Override -- To use this change Java Compiler compliance to 1.6 
	public Course mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		// TODO Auto-generated method stub			
		Course dbCourse = new Course();
		dbCourse.setCourseId(resultSet.getString(1));
		dbCourse.setCourseName(resultSet.getString(2));
		dbCourse.setCourseCategory(resultSet.getString(3));	
		dbCourse.setManagerId(resultSet.getString(4));
		dbCourse.setReleased(resultSet.getString(5));
		return dbCourse;	
	}
}
