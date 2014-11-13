package com.myprojects.tutorme.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.myprojects.tutorme.model.*;
import com.myprojects.tutorme.dao.courseDAO;

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
	public ModelAndView saveCourse(@ModelAttribute Course course, HttpServletRequest request)
	{
		System.out.println("Saving Course " + course.getCourseName());
		String currUser = (String) request.getSession().getAttribute("currEmail");
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config.xml");
		courseDAO courseDAO = ctx.getBean("courseDAO", courseDAO.class);	
		courseDAO.saveCourse(course, currUser);
		ModelAndView mv = new ModelAndView("homepageCM");
		return mv;	
	}
	
	@RequestMapping("/addQuiz")
	public ModelAndView addQuiz(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView("createQuiz");
		return mv;
	}
	
	@RequestMapping("/listCourses")
	public ModelAndView listCourses(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView("listCourses");
		String currEmailId = (String) request.getSession().getAttribute("currEmail");
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config.xml");
		courseDAO courseDAO = ctx.getBean("courseDAO", courseDAO.class);
		List<Course> allCourses = courseDAO.getCoursesForId(currEmailId);
		mv.addObject("coursesList", allCourses);
		//System.out.println("here" + allCourses.size());		
		return mv;
	}
	
	@RequestMapping(value = "/deleteCourse",  method = RequestMethod.GET)
	public ModelAndView deleteContact(HttpServletRequest request) {
		System.out.println("Gonna delete");
	    String courseId = request.getParameter("id");
	    ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config.xml");
		courseDAO courseDAO = ctx.getBean("courseDAO", courseDAO.class);
		courseDAO.deleteById(courseId);
	    return listCourses(request);
	}

	@RequestMapping(value="/viewCourse", method= RequestMethod.GET)
	public ModelAndView viewCourse(HttpServletRequest request) {
		String courseId = request.getParameter("id");	
		ModelAndView mv = new ModelAndView("viewCourse");
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config.xml");
		courseDAO courseDAO = ctx.getBean("courseDAO", courseDAO.class);
		List<CourseContent> allCourseContent = courseDAO.getAllContentForCourse(courseId);
		mv.addObject("contentList", allCourseContent);
		System.out.println("inViewCourses" + allCourseContent.size());
		return mv;
	}
}
