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
                  <li><a href="#">Account Settings</a></li>
                  <li><a href="logout">Logout</a></li>
                  <li class="divider"></li>
                  <li><a href="#">Help</a></li>
                </ul>
              </li>
              </ul>
          </div><!--/.nav-collapse -->
        </div><!--/.container-fluid -->
      </nav>

       <div class="jumbotron">        
        <a href="addCourse">Add content</a>
      </div>
      <table class="table">
    <thead>
        <tr>
            <th>Content Id</th>
            <th>Content Title</th>
            <th>Content Link</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="courseContent" items="${contentList}">
                <tr>
                <td>${courseContent.contentId}</td>
                <td>${courseContent.contentTitle}</td>
                <td><a href ="${courseContent.contentLink}">${courseContent.contentLink}</a></td>
                </tr>
       </c:forEach>   
    </tbody>
</table>


    </div> <!-- /container -->

</body>
</html>