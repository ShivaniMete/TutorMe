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
	
	public User checkIfExists(String userName, String password)
	{
		String sql = "SELECT * FROM UReg WHERE emailId = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);	
		List<User> myUsers = jdbcTemplate.query(sql, new Object[] { userName }, new UserRowMapper());
		System.out.println(myUsers.get(0).getActivated());
		if(myUsers.size()!=0 && myUsers.get(0).getEncryptedPassword().equals(EncryptWithMD5.cryptWithMD5(password)) && myUsers.get(0).getActivated().equals("Yes"))
		{
			System.out.println("Successfull..Yayy..!!" + myUsers.get(0).getFirstName());
			return myUsers.get(0);
		}
		else
			return null;
	}

	public void activateAccount(String userName, int acId)
	{
		//UPDATE ureg SET accountActivated = 'Yes' WHERE emailId=mete.shivani@gmail.com;
		String query = "UPDATE ureg SET accountActivated = 'Yes' WHERE emailId=?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		Object[] args = new Object[] {userName};
		int out = 0;
		if(userName.hashCode() == acId)
		{
			out = jdbcTemplate.update(query, args);
		}
		System.out.println("Updated " + out + " students");
	}

	/*public Boolean isAccountActivated(String userName)
	{
		String sql = "SELECT accountActivated FROM UReg WHERE emailId = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);	
		List<User> myUsers = jdbcTemplate.query(sql, new Object[] { userName }, new UserRowMapper());
		System.out.println(myUsers.size());

	}*/
}
