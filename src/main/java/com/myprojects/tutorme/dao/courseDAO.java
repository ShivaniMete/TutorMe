package com.myprojects.tutorme.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.myprojects.tutorme.model.Course;


public class courseDAO {
private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
	}
	public void save(Course course){
		String query = "insert into courses (courseId, courseName, courseCategory) values (?,?,?)";
        
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
         
        Object[] args = new Object[] {course.getCourseId(), course.getCourseName(), course.getCourseCategory()};
         
        int out = jdbcTemplate.update(query, args);
         
	}
	/*
	public User checkIfExists(String userName, String password)
	{
		String sql = "SELECT * FROM UReg WHERE emailId = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);	
		List<User> myUsers = jdbcTemplate.query(sql, new Object[] { userName }, new UserRowMapper());
		System.out.println(myUsers.size());
		if(myUsers.size()!=0 && myUsers.get(0).getEncryptedPassword().equals(EncryptWithMD5.cryptWithMD5(password)))
		{
			System.out.println("Successfull..Yayy..!!" + myUsers.get(0).getFirstName());
			return myUsers.get(0);
		}
		else
			return null;
	}
	*/
}
