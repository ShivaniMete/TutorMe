package com.myprojects.tutorme.dao;

import java.util.List;


import javax.sql.DataSource;


import org.springframework.jdbc.core.JdbcTemplate;

import com.myprojects.tutorme.model.StudentRegistration;

public class StudentsRegistrationDAO {
	private DataSource  dataSource;
	
	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
	}
	
	public List<StudentRegistration> checkIfRegistered(String courseID, String emailID){
		String query1 = "Select * from studentregistration where CourseId='";
		String query2 = "' AND emailId='";
		String query3 = "'";
		String query = query1 + courseID + query2 + emailID + query3;
		System.out.println("Trying " + query);
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<StudentRegistration> courses = jdbcTemplate.query(query, new StudentRegistrationRowMapper());
        System.out.println(courses.size() + " User's courses");
        return courses;
	}
	
	public void addRegistration(String id, String email)
	{
		String query = "insert into studentregistration (courseId, emailId, grades, completed) values (?,?,?,?)";
        
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
         
        Object[] args = new Object[] {id, email, "0", "No"};
         
        int out = jdbcTemplate.update(query, args);
	}
	
	public void deleteCourseByEmail(String id, String email)
	{
		String query = "delete from studentregistration where CourseId=? AND emailId=?";
        
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
         
        Object[] args = new Object[] {id, email};
         
        int out = jdbcTemplate.update(query, args);
        System.out.println(out + "deleted");
	}
}