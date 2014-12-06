package com.myprojects.tutorme.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
		HttpSession session = request.getSession();
		System.out.println(session.getAttribute("courseId"));
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
		List<Course> released = new ArrayList<Course>();
		List<Course> deprecated = new ArrayList<Course>();
		System.out.println("Making courses list");
		
		for (ListIterator<Course> iter = allCourses.listIterator(); iter.hasNext(); ) {
		    Course element = iter.next();
		  //  if(!element.getManagerId().equals(currEmailId))
		   // {
		   // 	iter.remove();
		  //  }
		    if(element.getReleased().equals("yes"))
		    {
		    	if(element.getDeprecated().equals("no"))
		    		released.add(element);
		    	else
		    		deprecated.add(element);
		    	
		    	iter.remove();
		    }
		}
		System.out.println("not released " + allCourses.size());
		System.out.println("released " + released.size());
		System.out.println("deprecated " + deprecated.size());
		
		mv.addObject("notReleasedList", allCourses);
		mv.addObject("releasedList", released);
		mv.addObject("deprecatedList", deprecated);
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
		request.getSession().setAttribute("courseId", courseId);
		ModelAndView mv = new ModelAndView("viewCourse");
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config.xml");
		courseDAO courseDAO = ctx.getBean("courseDAO", courseDAO.class);
		List<CourseContent> allCourseContent = courseDAO.getAllContentForCourse(courseId);
		List<CourseContent> quizContent = new ArrayList<CourseContent>();
		for (ListIterator<CourseContent> iter = allCourseContent.listIterator(); iter.hasNext(); ) {
			   CourseContent element = iter.next();
			   if(element.getContentType().equals("quiz"))
			   {
			   	quizContent.add(element);
			   	iter.remove();
			   	
			   }
			}
			mv.addObject("courseId", courseId);
			mv.addObject("contentList", allCourseContent);
			mv.addObject("quizList", quizContent);
			System.out.println("inViewCourses" + allCourseContent.size());
			return mv;
	}

	@RequestMapping("/saveContentQuiz")
	public ModelAndView saveQuiz(@ModelAttribute CourseContent content, BindingResult result, HttpServletRequest request)
	{
	if(result.hasErrors()){
	ModelAndView model1 = new ModelAndView("createQuiz");
	System.out.println("Error creating quiz.");
	return model1;
	}
	 
	String courseId = (String) request.getSession().getAttribute("courseId");
	//String contentId = content.getContentId();
	content.setCourseId(courseId);
	content.setContentType("quiz");
	System.out.println("Saving " + content.getContentId());
	ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config.xml");
	courseDAO courseDAO = ctx.getBean("courseDAO", courseDAO.class);
	//UserDAO userDAO = new UserDAO();
	courseDAO.saveQuiz(content);
	//return login();
	ModelAndView mv = new ModelAndView("createQuestion");
	mv.addObject("questionEntity", new QuizQuestion());
	mv.addObject("courseId", content.getCourseId());
	mv.addObject("quizId", content.getContentId());
	return mv;
	}
	
	@RequestMapping("/saveQuestion")
	public ModelAndView saveQuestion(@ModelAttribute QuizQuestion question, BindingResult result, HttpServletRequest request)
	{
	if(result.hasErrors()){
	ModelAndView model1 = new ModelAndView("createQuestion");
	model1.addObject("questionEntity", new QuizQuestion());
	model1.addObject("courseId", question.getCourseId());
	model1.addObject("quizId", question.getQuizId());
	model1.addObject("questionNum", question.getQuestionNum());
	System.out.println("Error creating question.");
	return model1;
	}
	 
	System.out.println("CourseId is: " + request.getParameter("courseId"));
	System.out.println("QuizId is: " + request.getParameter("quizId"));
	
	question.setCourseId(request.getParameter("courseId"));
	question.setQuizId(request.getParameter("quizId"));
	//question.setQuestionNum(request.getParameter("questionNum"));
	ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config.xml");
	courseDAO courseDAO = ctx.getBean("courseDAO", courseDAO.class);
	System.out.println(question.getQuestionNum() + "Question number");
	courseDAO.saveQuestion(question);
	/*ModelAndView mv = null;
	if (request.getParameter("endQuiz") != null) {
		System.out.println("ending....");		
		 mv = new ModelAndView("listCourses");
		//return mv;
	}
	
	else if (request.getParameter("createNext") != null) {
		System.out.println("ceating next");*/
	ModelAndView mv = new ModelAndView("createQuestion");
	mv.addObject("courseId", question.getCourseId());
	mv.addObject("quizId", question.getQuizId());
	
	return mv;
	}
	
	 @RequestMapping("/viewQuiz")
	 public ModelAndView viewQuiz(HttpServletRequest request)
	 {
		 System.out.println("In viewQuiz");
		 
	 ModelAndView mv = new ModelAndView("viewQuiz");
	 String courseId = request.getParameter("courseId");
	 String quizId = request.getParameter("quizId");
	 //System.out.println(request.getSession().getAttribute("currRole"));
	 ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config.xml");
	 courseDAO courseDAO = ctx.getBean("courseDAO", courseDAO.class);
	 List<QuizQuestion> questions = courseDAO.getQuizQuestions(courseId, quizId);
	 mv.addObject("questionList", questions);
	 mv.addObject("role", request.getSession().getAttribute("currRole"));
	 return mv;
	 }
	
	 @RequestMapping("/startQuiz")
	 public ModelAndView startQuiz(@ModelAttribute QuizQuestion question, HttpServletRequest request)
	 {
		 System.out.println("In startQuiz");
		 
	 ModelAndView mv = new ModelAndView("viewQuizQuestion");
	 String courseId = request.getParameter("courseId");
	 String quizId = request.getParameter("quizId");
	 int questionNum = Integer.parseInt(request.getParameter("questNum"));
	 String studentResponse = question.getAnswer();
	 //System.out.println(request.getSession().getAttribute("currRole"));
	 ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config.xml");
	 courseDAO courseDAO = ctx.getBean("courseDAO", courseDAO.class);
	 List<QuizQuestion> questions = courseDAO.getQuizQuestions(courseId, quizId);
	 //mv.addObject("questionList", questions);
	 //System.out.println(questions.get(questionNum).getQuestionNum());
	 mv.addObject("courseId", courseId);
	 mv.addObject("quizId", quizId);
	 mv.addObject("totalQuestions", questions.size());
	 float one = 1;
	 float percForOneAnswer = one/questions.size() * 100;
	 if(questionNum <= questions.size() +1 ){
		 System.out.println(questionNum);
		 String correctAnswer = null;
		 if(questionNum != 1)
			 correctAnswer = questions.get(questionNum-2).getAnswer();
		 System.out.println("Correct answer: "+ correctAnswer);
		 System.out.println("Response: " + studentResponse);
		 if(studentResponse != null){
		 if(studentResponse.equalsIgnoreCase(correctAnswer))
		 {
			 System.out.println("Correct Answer");
			 System.out.println(request.getSession().getAttribute("score"));
			 Object sessionScore = request.getSession().getAttribute("score");
			 if(sessionScore == null)// || Integer.parseInt(request.getSession().getAttribute("score").toString()) == 0)
			 {
				 request.getSession().setAttribute("score", percForOneAnswer);
			 }
			 else
			 {
				 float prevScore = Float.parseFloat(request.getSession().getAttribute("score").toString());
				 request.getSession().setAttribute("score", prevScore + percForOneAnswer );
			 }
		 }
		 }
	 }
	 System.out.println("Current score is: " + request.getSession().getAttribute("score"));
	 if(questionNum <= questions.size())
		 mv.addObject("question", questions.get(questionNum-1));
	 mv.addObject("currQues",questionNum);
	 mv.addObject("role", request.getSession().getAttribute("currRole"));
	 return mv;
	 }
	 
	 @RequestMapping("/saveContent")
		public ModelAndView saveContent(@ModelAttribute CourseContent content, BindingResult result, HttpServletRequest request)
		{
			String id = request.getParameter("id");
			 if(result.hasErrors()){
				 ModelAndView model1 = new ModelAndView("createContent");
				 //String id = request.getParameter("id");
				 System.out.println("Error saving content.");
				 model1.addObject("courseId", id);
				 return model1;
			}


			System.out.println("Saving " + content.getContentType());
			ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config.xml");
			courseDAO courseDAO = ctx.getBean("courseDAO", courseDAO.class);
			//UserDAO userDAO = new UserDAO();	
			//String courseId = request.getParmater("id");
			content.setCourseId(id);
			courseDAO.saveContent(content);
			
			ModelAndView mv = listCourses(request);
			return mv;
		}

		@RequestMapping(value="/deleteContent", method=RequestMethod.GET)
		public ModelAndView deleteContent(HttpServletRequest request)
		{
			String courseId = request.getParameter("courseId");
			String contentId= request.getParameter("contentId");
			System.out.println("deleting " + contentId + " from " + courseId);
			ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config.xml");
			courseDAO courseDAO = ctx.getBean("courseDAO", courseDAO.class);
			courseDAO.deleteContent(courseId, contentId);
			return listCourses(request);
		}

		@RequestMapping("/releaseCourse")
		public ModelAndView releaseCourse(HttpServletRequest request)
		{
			String course = request.getParameter("id");
			System.out.println("Releasing " + course);
			ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config.xml");
			courseDAO courseDAO = ctx.getBean("courseDAO", courseDAO.class);
			courseDAO.release(course);
			ModelAndView mv = listCourses(request);
			return mv;
		}
		
		@RequestMapping("/deprecateCourse")
		public ModelAndView deprecateCourse(HttpServletRequest request)
		{
			String course = request.getParameter("id");
			System.out.println("Deprecating " + course);
			ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config.xml");
			courseDAO courseDAO = ctx.getBean("courseDAO", courseDAO.class);
			courseDAO.deprecate(course);
			ModelAndView mv = listCourses(request);
			return mv;
		}
}
