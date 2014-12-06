package com.myprojects.tutorme.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.myprojects.tutorme.model.*;
import com.myprojects.tutorme.dao.StudentsRegistrationDAO;
import com.myprojects.tutorme.dao.UserDAO;
import com.myprojects.tutorme.dao.courseDAO;
import com.myprojects.tutorme.services.*;

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
	public ModelAndView save(@ModelAttribute User user, BindingResult result)
	{
		 if(result.hasErrors()){
			 ModelAndView model1 = new ModelAndView("registration");
			 System.out.println("Error saving registration.");
			 return model1;
		}

		if(!user.getConfirmPassword().equals(user.getPassword1()))
		{
			 ModelAndView model1 = new ModelAndView("registration");
			 return model1;
		}
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
			session.setAttribute("currEmail", currentUser.getEmailId());
			session.setAttribute("currName", currentUser.getFirstName());
			//System.out.println(currentUser.getRole() + "Current user role");
			session.setAttribute("currRole", currentUser.getRole());
			ModelAndView mv = new ModelAndView("redirect:/home");
			return mv;
		}
		else
		{
			ModelAndView mv = new ModelAndView("RedirectToHome");
			return mv;
		}
		
	}

	@RequestMapping("/home")
	public ModelAndView home(HttpServletRequest request) {
			HttpSession session = request.getSession();
			String currRole = session.getAttribute("currRole").toString();
			ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config.xml");
			if(currRole.equals("student"))
			{	
			System.out.println("Student role");
			ModelAndView mv = new ModelAndView("homepageStudent");				
			StudentsRegistrationDAO studentRegistrationDAO = ctx.getBean("studentsRegistrationDAO", StudentsRegistrationDAO.class);
			List<StudentRegistration> registeredCourseId = studentRegistrationDAO.getRegisteredCoursesForId(session.getAttribute("currEmail").toString());
			courseDAO courseD = ctx.getBean("courseDAO", courseDAO.class);
			List<CourseGrade> registeredCourses = new ArrayList<CourseGrade>();
			for(StudentRegistration sr:registeredCourseId ){
				CourseGrade courseGrades = new CourseGrade();
				Course courseInfo = courseD.getCourse(sr.getCourseID());
				courseGrades.setCourseId(courseInfo.getCourseId());
				courseGrades.setCourseName(courseInfo.getCourseName());
				courseGrades.setCourseCategory(courseInfo.getCourseCategory());
				courseGrades.setGrades(sr.getGrades());
				registeredCourses.add(courseGrades);
			}	
			mv.addObject("coursesList", registeredCourses);
			System.out.println("Added object courseList");
			return mv;
		}
		else if(currRole.equals("content manager")){
			System.out.println("CM role");
			ModelAndView mv = new ModelAndView("homepageCM");
			return mv;
		}
		else {
			System.out.println("Admin role");
			ModelAndView mv = new ModelAndView("homepageAdmin");
			ClassPathXmlApplicationContext obj = new ClassPathXmlApplicationContext("spring-config.xml");
			UserDAO usersDAO = obj.getBean("userDAO", UserDAO.class);
			List<User> studentList = usersDAO.getAllUsers();
			List<User> cmList = new ArrayList<User>();
			for (ListIterator<User> iter = studentList.listIterator(); iter.hasNext(); ) {
			    User element = iter.next();
			    if(element.getRole().equals("content manager"))
			    {
			    	cmList.add(element);
			    	iter.remove();
			    }
			    else if(element.getRole().equals("admin"))
			    {
			    	iter.remove();
			    }
			}
			System.out.println(studentList.size() + " content managers");
			System.out.println(cmList.size() + " students");
			mv.addObject("studentList", studentList);
			mv.addObject("cmList", cmList);
			return mv;
		}
		//System.out.println("From session: " + session.getAttribute("currRole"));		

		
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
	
	@RequestMapping("/switchToCM")
	public ModelAndView switchToCM(HttpServletRequest request){
		System.out.println(request.getParameter("id") + " switched to CM.");
		String id = request.getParameter("id");
		ModelAndView mv = new ModelAndView("homepageAdmin");
		ClassPathXmlApplicationContext obj = new ClassPathXmlApplicationContext("spring-config.xml");
		UserDAO usersDAO = obj.getBean("userDAO", UserDAO.class);
		usersDAO.changeToCM(id);
		List<User> studentList = usersDAO.getAllUsers();
		List<User> cmList = new ArrayList<User>();
		for (ListIterator<User> iter = studentList.listIterator(); iter.hasNext(); ) {
		    User element = iter.next();
		    if(element.getRole().equals("content manager"))
		    {
		    	cmList.add(element);
		    	iter.remove();
		    }
		    else if(element.getRole().equals("admin"))
		    {
		    	iter.remove();
		    }
		}
		System.out.println(studentList.size() + " content managers");
		System.out.println(cmList.size() + " students");
		mv.addObject("studentList", studentList);
		mv.addObject("cmList", cmList);
		return mv;
		
	}
	
	@RequestMapping("/switchToStudent")
	public ModelAndView switchToStudent(HttpServletRequest request){
		System.out.println(request.getParameter("id") + " switched to Student.");
		String id = request.getParameter("id");
		ModelAndView mv = new ModelAndView("homepageAdmin");
		ClassPathXmlApplicationContext obj = new ClassPathXmlApplicationContext("spring-config.xml");
		UserDAO usersDAO = obj.getBean("userDAO", UserDAO.class);
		usersDAO.changeToStudent(id);
		List<User> studentList = usersDAO.getAllUsers();
		List<User> cmList = new ArrayList<User>();
		for (ListIterator<User> iter = studentList.listIterator(); iter.hasNext(); ) {
		    User element = iter.next();
		    if(element.getRole().equals("content manager"))
		    {
		    	cmList.add(element);
		    	iter.remove();
		    }
		    else if(element.getRole().equals("admin"))
		    {
		    	iter.remove();
		    }
		}
		System.out.println(studentList.size() + " content managers");
		System.out.println(cmList.size() + " students");
		mv.addObject("studentList", studentList);
		mv.addObject("cmList", cmList);
		return mv;
		
	}
	
	@RequestMapping("/changePassword")
	public ModelAndView changePassword(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView("enterPassword");
		mv.addObject("passwordEntity", new Password());
		return mv;
	}
	
	@RequestMapping("/savePassword")
	public ModelAndView savePassword(@ModelAttribute Password password, BindingResult result, HttpServletRequest request)
	{
		String id = request.getParameter("id");
		//String email = (String) request.getAttribute("currEmail");
		System.out.println("About to save new Password for " + id);//request.getParameter("id"));
		 if(result.hasErrors()){
			 ModelAndView model1 = new ModelAndView("changePassword");
			 System.out.println("Error saving password.");
			 String message = "";
			 model1.addObject("userMessage", message);
			 return model1;
		}
		 
		if(!password.getPassword().equals(password.getConfirmPassword()))
		{
			System.out.println("passwords don't match.");
			ModelAndView model2 = new ModelAndView("enterPassword");
			String message = "Passwords must match";
			model2.addObject("userMessage", message);
			return model2;
		}
		
		String encryptedPassword = EncryptWithMD5.cryptWithMD5(password.getPassword());
		ClassPathXmlApplicationContext obj = new ClassPathXmlApplicationContext("spring-config.xml");
		UserDAO userDAO = obj.getBean("userDAO", UserDAO.class);
		userDAO.changePassword(encryptedPassword, id); //id);
		ModelAndView mv = new ModelAndView("redirect:/home");
		return mv;
	}
		
	@RequestMapping("/forgotPassword")
	public ModelAndView forgotPassword()
	{
		ModelAndView mv = new ModelAndView("forgetPassword");
		User user = new User();
		mv.addObject("userEntity", user);
		return mv;
	}
	
	@RequestMapping("/sendPassword")
	public ModelAndView sendPassword(@ModelAttribute User user, BindingResult result, HttpServletRequest request)
	{
		 if(result.hasErrors()){
			 ModelAndView model1 = new ModelAndView("forgetPassword");
			 System.out.println("Error saving username.");
			 return model1;
		}
		 
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config.xml");
		UserDAO userDAO = ctx.getBean("userDAO", UserDAO.class);
		System.out.println(request.getParameter("acid") + " in home Page");
		User currentUser = userDAO.checkIfUserExists(user.getEmailId());
		
		if(currentUser != null)
		{
			String password = UUID.randomUUID().toString();
			
			SendEmail.sendNewPassword(user.getEmailId(), password);
			String encryptedPassword = EncryptWithMD5.cryptWithMD5(password);
			ClassPathXmlApplicationContext objs = new ClassPathXmlApplicationContext("spring-config.xml");
			UserDAO usersDAO = objs.getBean("userDAO", UserDAO.class);
			usersDAO.changePassword(encryptedPassword, user.getEmailId());
			ModelAndView mv = new ModelAndView("RedirectToHome");
			return mv;
		}
		else
		{
			ModelAndView mv = new ModelAndView("forgetPassword");
			System.out.println("No user of this name");
			return mv;
		}
	}
	
	@RequestMapping("deleteUser")
	public ModelAndView deleteUser(HttpServletRequest request)
	{
		String id = request.getParameter("id");
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config.xml");
		UserDAO userDAO = ctx.getBean("userDAO", UserDAO.class);
		System.out.println("Deleting " + id);
		userDAO.deleteUser(id);
		ModelAndView mv = new ModelAndView("redirect:/home");
		return mv;
	}
	
	@RequestMapping("/returnToLogin")
	public ModelAndView returnToLogin()
	{
		ModelAndView mv = new ModelAndView("RedirectToHome");
		return mv;
	}
}
