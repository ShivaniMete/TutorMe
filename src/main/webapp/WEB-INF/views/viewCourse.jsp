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
              <li><a href="home">Home</a></li>
              <c:if test="${currRole=='content manager'}">
              <li><a href="listCourses">Courses</a></li>
              </c:if>
              <c:if test="${currRole=='student'}">
              <li><a href="availableCourses?email=${currEmail}">Available Courses</a></li>
              </c:if>
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
	<c:if test="${currRole=='content manager'}"> 
       <div class="jumbotron" style="padding: 15px;background-color: rgb(116, 109, 109);">     
        <a href="addCourse" class="btn btn-success">Add content</a>
        <a href="addQuiz?id=${courseId}" class="btn btn-success">Add Quiz</a>
      </div>
    </c:if>
    <c:if test="${currRole=='student'}"> 
      <div class="jumbotron" style="padding-top:10px; padding-bottom:10px; background-color: rgb(116, 109, 109);">        
        Course contents
      </div>
      </c:if>
<table class="table">
    <tbody>
        <c:forEach var="courseContent" items="${contentList}">
                <tr>
                <!-- <td>${courseContent.contentId}</td>
                <td>${courseContent.contentTitle}</td> 
                <td><a href ="${courseContent.contentLink}">${courseContent.contentLink}</a></td>-->
                <td>
                <iframe src="${courseContent.contentLink}" style="width:718px; height:350px; margin: 0 auto; display: block"></iframe>
                </td>
                </tr>
       </c:forEach>   
       <c:forEach var="quiz" items = "${quizList}">
      <tr>
      <td>${quiz.courseId}</td>
      <c:if test="${currRole=='content manager'}">
      <td><a href="viewQuiz?quizId=${quiz.contentId}&courseId=${quiz.courseId}">${quiz.contentId}</a></td>
      <td>${quiz.contentType}</td>
      </c:if>
      <c:if test="${currRole=='student'}">
      <td>${quiz.contentId}</td>
      <td><a href="startQuiz?quizId=${quiz.contentId}&courseId=${quiz.courseId}&questNum=1">Start Quiz</a></td>
      </c:if>
      
      </tr>
      </c:forEach>
	 
    </tbody>
</table>


    </div> <!-- /container --><!-- http://docs.google.com/gview?url=  &embedded=true-->  
</body>
</html>