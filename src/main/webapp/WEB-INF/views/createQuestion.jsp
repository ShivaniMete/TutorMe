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
<form class="form-signin" method="post" action="saveQuestion?courseId=${courseId}&quizId=${quizId}" modelAttribute ="questionEntity" role="form">
      <h2 class="form-signin-heading">Create a quiz question.</h2>
      		<div class="form-group">
              <input type="text" name= "questionNum" placeholder="Question Number" class="form-control" required>
            </div>
            <div class="form-group">
              <input type="text" name= "question" placeholder="Question" class="form-control" required>
            </div>
            <div class="form-group">
              <input type="text" name= "option1" placeholder="Option A" class="form-control" required>
            </div>
            <div class="form-group">
              <input type="text" name= "option2" placeholder="Option B" class="form-control" required>
            </div>
            <div class="form-group">
              <input type="text" name= "option3" placeholder="Option C" class="form-control" required>
            </div>         
            <div class="form-group">
              <input type="text" name="option4" placeholder="Option D" class="form-control" required>
            </div>
            <div class="form-group">
            <input type="text" name="answer" placeholder="A, B, C, or D (choose one)" class="form-control" required>
            </div>
            <div style="display:inline-block">
            <button type="submit" class="btn btn-success" name="createNext" value="create Next button">Save and Create Next</button>
            <!-- <button type="submit" class= "btn btn-success" name="endQuiz" value="end quiz buttton">Save and End quiz</button> -->
            </div>
         </form>
   </div>  
   <a href="listCourses">End Quiz Creation</a>
    <script src="http://getbootstrap.com/assets/js/ie10-viewport-bug-workaround.js"></script>
       
</body>
</html>