package com.myprojects.tutorme.dao;

import java.util.List;



import javax.sql.DataSource;



import org.springframework.jdbc.core.JdbcTemplate;

import com.myprojects.tutorme.model.Course;
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
	
	public List<StudentRegistration> getRegisteredCoursesForId(String emailId){
		System.out.println("getting registered courses for student");
		String query = "Select * from studentregistration where emailId =?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<StudentRegistration> courses = jdbcTemplate.query(query, new Object[] {emailId}, new StudentRegistrationRowMapper());
        System.out.println(courses.size() + " Courses");
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
	
	public StudentRegistration getRegInfo(String courseId, String emailId)
	{
		String query = "Select * from studentregistration where CourseId=? and emailId=?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<StudentRegistration> regInfo = jdbcTemplate.query(query, new Object[] {courseId, emailId}, new StudentRegistrationRowMapper());
        if(regInfo.size() != 0)
        	return regInfo.get(0);
        else
        	return null;
	}
	
	public void updateGrades(String courseId, String emailId, String newGrades, Boolean isCompleted)
	{
		String query;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		if(isCompleted)
		{
			query = "update studentregistration SET grades = ? where CourseId=? AND emailId=?";
		}
		else
		{
			query = "update studentregistration SET grades = ?, completed = 'Yes' where CourseId=? AND emailId=?";
		}        
		System.out.println(query);
		Object[] args = new Object[] {newGrades, courseId, emailId};
        int out = jdbcTemplate.update(query, args);
	}
}