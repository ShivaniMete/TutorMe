package com.myprojects.tutorme.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.myprojects.tutorme.model.*;
import com.myprojects.tutorme.dao.UserDAO;

import org.springframework.context.support.ClassPathXmlApplicationContext;
@Controller
public class UserController {
	@RequestMapping("/home")
	public ModelAndView login12(){
		System.out.println("In user controller");
		//TODO:call model here
		//calling the desired view
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config.xml");
		UserDAO userDAO = ctx.getBean("userDAO", UserDAO.class);
		//UserDAO userDAO = new UserDAO();
		User newUser = new User();
		newUser.setEmailId("abc.123@gmail.com");
		newUser.setFirstName("Brad");
		newUser.setLastName("Charles");
		newUser.setPhoneNumber("122456789");
		newUser.setEncryptedPassword(EncryptWithMD5.cryptWithMD5("brad01"));
		
		userDAO.save(newUser);
		ModelAndView mv = new ModelAndView("homepage");
		return mv;
	}
	
	@RequestMapping("/register")
	public ModelAndView showRegistration()
	{
		//System.out.println(user.getFirstName());
		ModelAndView ind = new ModelAndView("registration");
		ind.addObject("userEntity", new User());
		return ind;
	}
	
	@RequestMapping("/save")
	public ModelAndView save(@ModelAttribute User user)
	{
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config.xml");
		UserDAO userDAO = ctx.getBean("userDAO", UserDAO.class);
		//UserDAO userDAO = new UserDAO();		
		userDAO.save(user);
		ModelAndView mv = new ModelAndView("homepage");
		return mv;
	}
	
	@RequestMapping("/login")
	public ModelAndView login()
	{
		//System.out.println(user.getFirstName());
		ModelAndView ind = new ModelAndView("login");
		ind.addObject("loginEntity", new User());
		return ind;
	}
	
	@RequestMapping("/checkLogin")
	public ModelAndView checkLogin(@ModelAttribute User user)
	{
		System.out.println(user.getEmailId());
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config.xml");
		UserDAO userDAO = ctx.getBean("userDAO", UserDAO.class);
		if(userDAO.checkIfExists(user.getEmailId(), user.getEncryptedPassword()))
		{
			System.out.println("Success");
			ModelAndView mv = new ModelAndView("homepage");
			return mv;
		}
		else
		{
			return login();
		}
		
	}
	
}
