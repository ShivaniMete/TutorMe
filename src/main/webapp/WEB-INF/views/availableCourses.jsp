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
              <li class="active"><a href="availableCourses?email=${currEmail}">Available Courses</a></li>
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
      <table class="table">
    <thead>
        <tr>
            <th>Course Id</th>
            <th>Course Name</th>
            <th>Course Category</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="course" items="${availableList}">
                <tr>
                <td>${course.courseId}</td>
                    <td>${course.courseName}</td>
                    <td>${course.courseCategory}</td>
                    <td>
                        <a href="registerForCourse?id=${course.courseId}&userEmail=${currEmail}">Add</a>
                    </td>
                </tr>
       </c:forEach> 
       <c:forEach var="courses" items="${registeredList}">
                <tr>
                <td>${courses.courseId}</td>
                    <td>${courses.courseName}</td>
                    <td>${courses.courseCategory}</td>
                    <td>
                        <a href="dropCourse?id=${courses.courseId}&userEmail=${currEmail}">Drop</a>
                    </td>
                </tr>
       </c:forEach>  
    </tbody>
</table>

    </div> <!-- /container -->
</div>
</body>
</html>