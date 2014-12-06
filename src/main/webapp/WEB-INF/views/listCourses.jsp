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
              <li class="active"><a href="listCourses">Courses</a></li>
              <li><a href="#">Certificates</a></li>             
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

      <!-- Main component for a primary marketing message or call to action -->
      <c:if test="${currRole=='content manager'}"> 
      <div class="jumbotron" style="padding: 15px;background-color: rgb(116, 109, 109);">        
        <a href="addCourse"  class="btn btn-success">Add course</a>
      </div> 
      </c:if>
      <c:if test="${currRole=='student'}"> 
      <div class="jumbotron" style="padding-top:10px; padding-bottom:10px">        
        Current courses
      </div>
      </c:if>
     <table class="table">
    <thead>
        <tr>
            <th>Course Id</th>
            <th>Course Name</th>
            <th>Course Category</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="courses2" items="${notReleasedList}">
                <tr>
                <td>${courses2.courseId}</td>
                    <td><a href="viewCourse?id=${courses2.courseId}">${courses2.courseName}</a></td>
                    <td>${courses2.courseCategory}</td>
                    <td>
                    	<a href="releaseCourse?id=${courses2.courseId}">Release</a>
                    </td>
                    <td>
                        <a href="deleteCourse?id=${courses2.courseId}">Delete</a>
                    </td>
                </tr>
       </c:forEach>   
               <c:forEach var="courses3" items="${releasedList}">
                <tr>
                <td>${courses3.courseId}</td>
                    <td><a href="viewCourse?id=${courses3.courseId}">${courses3.courseName}</a></td>
                    <td>${courses3.courseCategory}</td>
                    <td>
                    	<a href="deprecateCourse?id=${courses3.courseId}">Deprecate</a>
                    </td>
                    <td>
                        <a href="deleteCourse?id=${courses3.courseId}">Delete</a>
                    </td>
                </tr>
       </c:forEach>
               <c:forEach var="course" items="${deprecatedList}">
                <tr>
                <td>${course.courseId}</td>
                    <td><a href="viewCourse?id=${course.courseId}">${course.courseName}</a></td>
                    <td>${course.courseCategory}</td>
                    <td>Deprecated</td>
                    <td>
                        <a href="deleteCourse?id=${course.courseId}">Delete</a>
                    </td>
                </tr>
       </c:forEach>
    </tbody>
</table>

    </div> <!-- /container -->
</div>
</body>
</html>