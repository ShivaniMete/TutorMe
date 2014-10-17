package com.myprojects.tutorme.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.myprojects.tutorme.model.User;
import com.myprojects.tutorme.model.EncryptWithMD5;

public class UserDAO {
private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
	}
	public void save(User user){
		String query = "insert into UReg (emailId, fName,lName,phoneNumber,password) values (?,?,?,?,?)";
        
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
         
        Object[] args = new Object[] {user.getEmailId(), user.getFirstName(), user.getLastName(), user.getPhoneNumber(), user.getEncryptedPassword()};
         
        int out = jdbcTemplate.update(query, args);
         
        if(out !=0){
            System.out.println("Employee saved with name= "+ user.getFullName());
        }else System.out.println("Employee save failed with name= "+ user.getFullName());
	}
	
	@SuppressWarnings("unchecked")
	public boolean checkIfExists(String userName, String password)
	{
		String sql = "SELECT * FROM UReg WHERE emailId = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);	
		User myUser = (User) jdbcTemplate.queryForObject(sql, new Object[] { userName }, new UserRowMapper());
		if(myUser.getEncryptedPassword().equals(EncryptWithMD5.cryptWithMD5(password)))
		{
			System.out.println("Successfull..Yayy..!!" + myUser.getEmailId());
			return true;
		}
		else
			return false;
	}
}
