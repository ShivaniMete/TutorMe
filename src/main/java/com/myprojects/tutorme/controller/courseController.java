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
import com.myprojects.tutorme.dao.courseDAO;
import com.myprojects.tutorme.services.SendEmail;
import com.myprojects.tutorme.*;

import org.springframework.context.support.ClassPathXmlApplicationContext;
@Controller
public class courseController {
	@RequestMapping("/addCourse")
	public ModelAndView showRegistration()
	{
		//System.out.println(user.getFirstName());
		ModelAndView ind = new ModelAndView("addCourse");
		ind.addObject("courseEntity", new Course());
		return ind;
	}
	
	@RequestMapping("/saveCourse")
	public ModelAndView saveCourse(@ModelAttribute Course course)
	{
		System.out.println("Saving Course " + course.getCourseName());
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config.xml");
		courseDAO courseDAO = ctx.getBean("courseDAO", courseDAO.class);
		//UserDAO userDAO = new UserDAO();		
		courseDAO.saveCourse(course);
		//SendEmail.sendTo(user.getEmailId());
		//return login();
		ModelAndView mv = new ModelAndView("homepageCM");
		return mv;	
	}
}
