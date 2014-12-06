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
	public void saveCourse(Course course, String managerId){
		String query = "insert into courses (courseId, courseName, courseCategory, managerId) values (?,?,?,?)";
        
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
         
        Object[] args = new Object[] {course.getCourseId(), course.getCourseName(), course.getCourseCategory(), managerId};
         
        int out = jdbcTemplate.update(query, args);         
	}
	
	public List<Course> getCoursesForId(String managerId){
		String query = "Select * from courses where managerId =?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Course> courses = jdbcTemplate.query(query, new Object[] {managerId}, new CourseRowMapper());
        System.out.println(courses.size() + "Courses");
        return courses;
	}
	
	public Course getCourse(String courseId){
		String query = "Select * from courses where courseId =?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Course> courses =  jdbcTemplate.query(query, new Object[] {courseId}, new CourseRowMapper());
        //System.out.println(courses.getCourseId());
        return courses.get(0);
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

	public void saveQuestion(QuizQuestion question)
	{
	String query="insert into quizquestions (courseId,quizId,questionNum, question,option1,option2,option3,option4,answer) values (?,?,?,?,?,?,?,?,?)";
	 
	JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
	 
	Object[] args = new Object[] {question.getCourseId(), question.getQuizId(), question.getQuestionNum(),question.getQuestion(),
	question.getOption1(), question.getOption2(), question.getOption3(), question.getOption4(), question.getAnswer()};
	int out = jdbcTemplate.update(query, args);
	}

	public void saveQuiz(CourseContent content)
	{
	String query = "insert into coursecontent (courseId, contentId, contentType, contentLink) values (?,?,?,?)";
	 
	JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
	 
	Object[] args = new Object[] {content.getCourseId(), content.getContentId(), content.getContentType(),
	content.getContentLink()};
	System.out.println("About to send query");
	int out = jdbcTemplate.update(query, args);
	}
	
	 public List<QuizQuestion> getQuizQuestions(String courseId, String quizId)
	 {
	 String query = "Select * from quizquestions where courseId = ? AND quizId = ?";
	 JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
	 List<QuizQuestion> questions = jdbcTemplate.query(query, new Object[] {courseId, quizId}, new QuizQuestionRowMapper());
	 System.out.println("Questions " + questions.size());
	 return questions;
	 }
	 
	 public void saveContent(CourseContent content)
		{
			String query = "insert into coursecontent (courseId, contentId, contentType, contentLink) values (?,?,?,?)";
			
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			
			Object[] args = new Object[] {content.getCourseId(), content.getContentId(), content.getContentType(), content.getContentLink()};
			
			int out = jdbcTemplate.update(query, args);
		}

	public void release(String id)
		{
			String query = "UPDATE courses SET released='yes' WHERE courseId=?";
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			
			Object[] args = new Object[] {id};
			
			int out = jdbcTemplate.update(query, args);
			System.out.println("released " + out + id);
		}
		
		public void deprecate(String id)
		{
			String query = "UPDATE courses SET deprecated='yes' WHERE courseId=?";
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			
			Object[] args = new Object[] {id};
			
			int out = jdbcTemplate.update(query, args);
			System.out.println("deprecated " + out + id);
		}
		
		public void deleteContent(String courseId, String contentId)
		{
			String query = "delete from courseContent where courseId=? AND contentId=?";
			
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			
			Object[] args = new Object[] {courseId, contentId};
			int out = jdbcTemplate.update(query, args);
			System.out.println(out + " deleted");
		}
}
