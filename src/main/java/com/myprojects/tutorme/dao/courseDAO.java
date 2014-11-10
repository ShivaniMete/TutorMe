package com.myprojects.tutorme.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.myprojects.tutorme.model.*;


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
	
	public List<Course> getAllCourses(){
		String query = "Select * from courses";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Course> courses = jdbcTemplate.query(query, new CourseRowMapper());
        System.out.println(courses.size() + "Courses");
        return courses;
	}
	
	public void deleteById(String courseId) {
		String query = "delete from courses where courseId=?";
        
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
         
        Object[] args = new Object[] {courseId};
         
        int out = jdbcTemplate.update(query, args);
        System.out.println(out + "deleted");
	}
	
	public List<CourseContent> getAllContentForCourse(String courseId){
		String query = "Select * from coursecontent where courseId=?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<CourseContent> courseContent = jdbcTemplate.query(query,new Object[] {courseId},new CourseContentRowMapper());
        System.out.println(courseContent.size() + "CourseContent");
		return courseContent;
	}
}
