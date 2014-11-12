package com.myprojects.tutorme.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.myprojects.tutorme.model.*;
import com.myprojects.tutorme.dao.UserDAO;
import com.myprojects.tutorme.dao.courseDAO;
import com.myprojects.tutorme.dao.StudentsRegistrationDAO;
import com.myprojects.tutorme.model.StudentRegistration;

@Controller
public class StudentRegistrationController {
	@RequestMapping(value = "/registerForCourse",  method = RequestMethod.GET)
	public ModelAndView registerForCourse(HttpServletRequest request) {
	    String courseId = request.getParameter("id");
	    String emailId = request.getParameter("userEmail");
	    System.out.println("Gonna register " + courseId + " for " + emailId);
	    ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config.xml");
		System.out.println("Saving  " + courseId + " for " + emailId);
		
		StudentsRegistrationDAO studentRegistrationDAO = ctx.getBean("studentsRegistrationDAO", StudentsRegistrationDAO.class);
		
		studentRegistrationDAO.addRegistration(courseId, emailId);
		ModelAndView mv = new ModelAndView("homepageStudent");
		return mv;
	}
	//change to delete from the registeredcourses table
	@RequestMapping(value = "/dropCourse",  method = RequestMethod.GET)
	public ModelAndView dropCourse(HttpServletRequest request) {
		System.out.println("Gonna drop course ");
	    String courseId = request.getParameter("id");
	    String emailId = request.getParameter("userEmail");
	    ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config.xml");
		StudentsRegistrationDAO registrationDAO = ctx.getBean("studentsRegistrationDAO", StudentsRegistrationDAO.class);
		
		System.out.println("Deleting " + courseId + emailId);
		
		registrationDAO.deleteCourseByEmail(courseId, emailId);
	    ModelAndView mv = new ModelAndView("homepageStudent");
	    return mv;
	}
	
	@RequestMapping(value = "/availableCourses", method = RequestMethod.GET) 
	public ModelAndView availableCourses(HttpServletRequest request)//HttpSession session)
	{
		ModelAndView mv = new ModelAndView("availableCourses");
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config.xml");
		courseDAO courseDAO = ctx.getBean("courseDAO", courseDAO.class);
		StudentsRegistrationDAO registrationDAO = ctx.getBean("studentsRegistrationDAO", StudentsRegistrationDAO.class);
		List<Course> availableCourses = courseDAO.getAllCourses();
		List<Course> registeredCourses = new ArrayList<Course>();
		List<StudentRegistration> retrieved;
		String emailId = request.getParameter("email");
		System.out.println("Current email is :");
		System.out.println(emailId);
		for (ListIterator<Course> iter = availableCourses.listIterator(); iter.hasNext(); ) {
		    Course element = iter.next();
		    if(element.getReleased().equals("yes"))
		    {
		    	retrieved = registrationDAO.checkIfRegistered(element.getCourseId(), emailId); //how to get email ID?
		    			//decide if this current user is in this class or not.
		    	if(retrieved.size() > 0)
		    	{
		    		registeredCourses.add(element);
		    		iter.remove();
		    	}
		    }
		    else
		    	iter.remove();
		}
		//Delete all of the courses that have not been released
		mv.addObject("availableList", availableCourses);
		mv.addObject("registeredList", registeredCourses);
		System.out.println("here" + availableCourses.size());		
		return mv;
	}

}
