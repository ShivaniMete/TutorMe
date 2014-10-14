package com.myprojects.tutorme.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.myprojects.tutorme.model.*;
import com.myprojects.tutorme.dao.UserDAO;

import org.springframework.context.support.ClassPathXmlApplicationContext;
@Controller
public class UserController {
	@RequestMapping("/home")
	public ModelAndView login(){
		System.out.println("In user controller");
		//TODO:call model here
		//calling the desired view
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config.xml");
		UserDAO userDAO = ctx.getBean("userDAO", UserDAO.class);
		//UserDAO userDAO = new UserDAO();
		User newUser = new User();
		newUser.setEmailId("tale.swapnil@gmail.com");
		newUser.setFirstName("Swapnil");
		newUser.setLastName("Tale");
		newUser.setPhoneNumber("2153007109");
		newUser.setEncryptedPassword(EncryptWithMD5.cryptWithMD5("swapnil25"));
		
		userDAO.save(newUser);
		ModelAndView mv = new ModelAndView("homepage");
		return mv;
	}


}
