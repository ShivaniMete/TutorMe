package com.myprojects.tutorme.controller;


import java.util.UUID;

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
		SendEmail.sendTo(user.getEmailId(), user.getEmailId().hashCode());
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
	public ModelAndView checkLogin(@ModelAttribute User user, HttpServletRequest request)
	{
		System.out.println(user.getEmailId());
		System.out.println(user.getEmailId().hashCode());//622042148
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config.xml");
		UserDAO userDAO = ctx.getBean("userDAO", UserDAO.class);
		System.out.println(request.getParameter("acid") + " in home Page");
		User currentUser = userDAO.checkIfExists(user.getEmailId(), user.getEncryptedPassword());
		//TODO: Check if account is activated. If yes then execute below if block, else get acid from the request
		//and activate the account.		
		if(currentUser != null)
		{ 
			System.out.println("Success");
			HttpSession session = request.getSession();
			session.setAttribute("currentUser", currentUser);
			session.setAttribute("currName", currentUser.getFirstName());
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
	
	@RequestMapping("/activateAccount")
	public ModelAndView activate(HttpServletRequest request){
		System.out.println(request.getParameter("acid") + " in activate account Page");
		System.out.println(request.getParameter("uid") + " in activate account Page");
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config.xml");
		UserDAO userDAO = ctx.getBean("userDAO", UserDAO.class);
		userDAO.activateAccount(request.getParameter("uid"), Integer.parseInt(request.getParameter("acid")));
		ModelAndView mv = new ModelAndView("RedirectToHome");
		return mv;		
	}
}
