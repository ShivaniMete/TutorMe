<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">


<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>

</head>

<body>
<div class="container">

      <form class="form-signin" method="post" action="save" modelAttribute ="userEntity" role="form">
      <h2 class="form-signin-heading">Please sign up</h2>
            <div class="form-group">
              <input type="text" name= "courseId" placeholder="Course ID" class="form-control">
            </div>
            <div class="form-group">
              <input type="text" name= "courseName" placeholder="Course Name" class="form-control">
            </div>
            <div class="form-group">
              <input type="text" name= "courseCategory" placeholder="Course Category" class="form-control">
            </div>
            <button type="submit" class="btn btn-success" value="Add Course">Add Course</button>
          </form>        
    </div>
    <script src="http://getbootstrap.com/assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>