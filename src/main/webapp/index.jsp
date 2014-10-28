
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>

<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">

<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>

</head>

<body>
    <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="">TutorMe</a>
        </div>
        <div class="navbar-collapse collapse">
          <form class="navbar-form navbar-right" method ="post" action="checkLogin" modelAttribute="loginEntity" role="form">
            <div class="form-group">
              <input type="text" name= "emailId" placeholder="Email" class="form-control">
            </div>
            <div class="form-group">
              <input type="password" name="password" placeholder="Password" class="form-control">
            </div>
            <button type="submit" class="btn btn-success" value="Login">Sign in</button>
            <a href="register">New User?</a>
          </form>
        </div><!--/.navbar-collapse -->
      </div>
    </div>
<div class="jumbotron">
      <div class="container">
        <h1>Tutor Me</h1>
        <p>We're a tutoring academy with the goal of changing education for the better by providing a free world-class education for anyone anywhere.
        All of the site's resources are available to anyone. It doesn't matter if you are a student, teacher, home-schooler, principal, adult returning to the classroom after 20 years, or a friendly alien just trying to get a leg up in earthly biology.Our resources are always at your disposal.</p>
     </div>
    </div>
    <div class="container">
      <!-- Example row of columns -->
      <div class="row">
        <div class="col-md-3">
          <h2>24 x 7</h2>
          <p>School is tough. Getting a tutor with us is easy. Get a real tutor anytime, anywhere in our online classroom.</p>
        </div>
        <div class="col-md-3">
          <h2>The Best Tutors</h2>
          <p>From Ph.D.s and Ivy Leagues to teachers, doctors, professors and pilots, our tutors are the best.</p>
       </div>
        <div class="col-md-3">
          <h2>Variety of Subjects</h2>
          <p>From Algebra, Calculus and Statistics to English, Chemistry and Physics. Prepare using various tutorials and practice tests.</p>
          
        </div>
        <div class="col-md-3">
          <h2>Certificates</h2>
          <p>Best of all! Receive a verified university level certification for all the completed courses.</p>          
        </div>
     </div>
      
      <hr>

      <footer>
        <p>&copy;Tutor Me 2014</p>
      </footer>
    </div>
      <!-- <h2>For Students</h2>
		<h3>
			<a href="login">Click Here to Login to you account</a>
		</h3>-->		
</body>
</html>
