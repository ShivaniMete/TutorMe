package com.myprojects.tutorme.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.myprojects.tutorme.model.*;
import com.myprojects.tutorme.dao.UserDAO;
import com.myprojects.tutorme.services.SendEmail;

import org.springframework.context.support.ClassPathXmlApplicationContext;
@Controller
public class UserController {
	
	@RequestMapping("/register")
	public ModelAndView showAddCourse()
	{
		//System.out.println(user.getFirstName());
		ModelAndView ind = new ModelAndView("registration");
		ind.addObject("userEntity", new User());
		return ind;
	}
	
	@RequestMapping("/save")
	public ModelAndView save(@ModelAttribute User user)
	{
		System.out.println("Saving " + user.getEmailId());
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config.xml");
		UserDAO userDAO = ctx.getBean("userDAO", UserDAO.class);
		//UserDAO userDAO = new UserDAO();		
		userDAO.save(user);
		SendEmail.sendTo(user.getEmailId());
		//return login();
		ModelAndView mv = new ModelAndView("RedirectToHome");
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
	public ModelAndView checkLogin(@ModelAttribute User user, HttpSession session)
	{
		System.out.println(user.getEmailId());
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config.xml");
		UserDAO userDAO = ctx.getBean("userDAO", UserDAO.class);
		User currentUser = userDAO.checkIfExists(user.getEmailId(), user.getEncryptedPassword());
		if(currentUser != null)
		{ 
			System.out.println("Success");
			session.setAttribute("currentUser", currentUser);
			//User currUser = (User)session.getAttribute("currentUser");
			session.setAttribute("currName", currentUser.getFirstName());
			//session.setAttribute("currRole", currentUser.getRole());
			if(currentUser.getRole().equals("student")){
				System.out.println("Student role");
				ModelAndView mv = new ModelAndView("homepageStudent");
				return mv;
			}
			else if(currentUser.getRole().equals("content manager")){
				System.out.println("CM role");
				ModelAndView mv = new ModelAndView("homepageCM");
				return mv;
			}
			else {
				System.out.println("Admin role");
				ModelAndView mv = new ModelAndView("homepageCM");
				return mv;
			}
			//System.out.println("From session: " + session.getAttribute("currRole"));		
		}
		else
		{
			ModelAndView mv = new ModelAndView("RedirectToHome");
			return mv;
		}
		
	}

	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest request){
		System.out.println("In logout");
		HttpSession session = request.getSession();
		session.invalidate();
		//System.out.println(session.getAttribute("currName"));
		ModelAndView mv = new ModelAndView("RedirectToHome");
		return mv;
	}
}
