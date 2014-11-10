package com.myprojects.tutorme.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.myprojects.tutorme.model.CourseContent;

public class CourseContentRowMapper implements RowMapper<CourseContent>{
	public CourseContent mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		// TODO Auto-generated method stub			
		CourseContent dbCourseContent = new CourseContent();
		dbCourseContent.setCourseId(resultSet.getString(1));
		dbCourseContent.setContentId(resultSet.getString(2));
		dbCourseContent.setContentTitle(resultSet.getString(3));
		dbCourseContent.setContentType(resultSet.getString(4));
		dbCourseContent.setContentLink(resultSet.getString(5));
		return dbCourseContent;	
	}

}
