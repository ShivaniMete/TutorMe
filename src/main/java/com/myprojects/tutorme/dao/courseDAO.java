package com.myprojects.tutorme.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.myprojects.tutorme.model.Course;


public class courseDAO {
private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
	}
	public void saveCourse(Course course){
		String query = "insert into courses (courseId, courseName, courseCategory) values (?,?,?)";
        
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
         
        Object[] args = new Object[] {course.getCourseId(), course.getCourseName(), course.getCourseCategory()};
         
        int out = jdbcTemplate.update(query, args);
         
	}
	
}
