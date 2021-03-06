<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
</head>

<body>
<div class="container">

      <!-- Static navbar -->
      <nav class="navbar navbar-default" role="navigation">
        <div class="container-fluid">
          <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Tutor Me</a>
          </div>
          <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
              <li><a href="#">Home</a></li>
              <li><a href="listCourses">Courses</a></li>
              <li class="active"><a href="viewCourse">Content</a></li>             
            </ul>
            <ul class="nav navbar-nav navbar-right">
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Welcome, <c:out value="${currName}"/> <span class="caret"></span></a>
                <ul class="dropdown-menu" role="menu">
                  <li><a href="changePassword">Change Password</a></li>
                  <li><a href="logout">Logout</a></li>
                  <li class="divider"></li>
                  <li><a href="#">Help</a></li>
                </ul>
              </li>
              </ul>
          </div><!--/.nav-collapse -->
        </div><!--/.container-fluid -->
      </nav>

<c:set var="currQuest" value="${currQues}"/>
<c:set var="totalQuest" value="${totalQuestions}"/>

<c:if test="${currQuest > totalQuest}">
<a href="submitQuiz?courseId=${courseId}" class="btn btn-success">End Quiz</a>
</c:if>
<c:if test="${currQuest <= totalQuest}">
<form method="post" action="startQuiz?quizId=${quizId}&courseId=${courseId}&questNum=${question.questionNum +1}">
<table class="table">
     <tbody class="pull-left">
	     	<tr>
	     		 <td>${question.questionNum}. ${question.question}</td>	     		
	     	</tr>
	     	<tr>
	     	    <td style="width:200px">
	     	    	<input class="radio-inline" type="radio" value="A" name="answer">&nbsp;&nbsp;A. ${question.option1}</td>	
	     	
	     	    <td style="width:200px">
	     			<input class="radio-inline" type="radio" value="B" name="answer">&nbsp;&nbsp;B. ${question.option2}</td>	
	     	
	     	    <td style="width:200px">
	     			<input class="radio-inline" type="radio" value="C" name="answer">&nbsp;&nbsp;C.${question.option3}</td>	
	     	
	     	    <td>
	     			<input class="radio-inline" type="radio" value="D" name="answer">&nbsp;&nbsp;D. ${question.option4}</td>	
	     	</tr>
	     	</tbody>
</table>
<input type="submit" name="saveResponse" value="Save Response">
</form>
</c:if>
</div> <!-- Container -->

</body>
</html>