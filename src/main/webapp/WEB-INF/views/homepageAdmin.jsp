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
            <div class="jumbotron">        
        <p>Students</p>
      </div>
      <table class="table">
    <thead>
        <tr>
            <th>User Id</th>
            <th>Current Role</th>
            <th>Change Role To:</th>
        </tr>
    </thead>
    <tbody>
         <c:forEach var="student" items="${studentList}">
                <tr>
                <td>${student.emailId}</td>
                    <td>${student.role}</td>
                    <td>
                        <a href="switchToCM?id=${student.emailId}">Change To Content Manager</a>
                    </td>
                    <td>
                    	<a href="deleteUser?id=${student.emailId}">Delete</a>
                    </td>
                </tr>
       </c:forEach> 
    </tbody>
</table>

      <div class="jumbotron">        
        </small><p>Content Managers</p>
      </div>
      <table class="table">
    <thead>
        <tr>
            <th>User Id</th>
            <th>Current Role</th>
            <th>Change Role To:</th>
        </tr>
    </thead>
          <c:forEach var="cm" items="${cmList}">
                <tr>
                <td>${cm.emailId}</td>
                    <td>${cm.role}</td>
                    <td>
                        <a href="switchToStudent?id=${cm.emailId}">Change To Student</a>
                    </td>
                    <td>
                    	<a href="deleteUser?id=${cm.emailId}">Delete</a>
                    </td>
                </tr>
       </c:forEach> 
    </tbody>
</table>

    </div> <!-- /container -->
</div>
</body>
</html>