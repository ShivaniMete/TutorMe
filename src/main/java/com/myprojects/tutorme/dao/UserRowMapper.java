package com.myprojects.tutorme.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.myprojects.tutorme.model.User;

public class UserRowMapper implements RowMapper<User>{

	//@Override -- To use this change Java Compiler compliance to 1.6 
	public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		// TODO Auto-generated method stub			
		User dbUser = new User();
		dbUser.setEmailId(resultSet.getString(1));
		dbUser.setPassword(resultSet.getString(5));
		//System.out.println("Counter is: " + count);
		return dbUser;	
	}
}
