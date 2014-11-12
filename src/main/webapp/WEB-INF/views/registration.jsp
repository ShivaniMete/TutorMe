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
              <input type="text" name= "firstName" placeholder="First Name" class="form-control" required>
            </div>
            <div class="form-group">
              <input type="text" name= "lastName" placeholder="Last Name" class="form-control" required>
            </div>
            <div class="form-group">
              <input type="email" name= "emailId" placeholder="Email address" class="form-control" required>
            </div>
            <div class="form-group">
              <input type="text" name= "phoneNumber" placeholder="Contact number" class="form-control" required>
            </div>
            <div class="form-group">
              <input type="password" name="encryptedPassword" placeholder="Password" class="form-control" required>
            </div>
            <div class="form-group">
              <input type="password" name="confirmPassword" placeholder="Conform Password(must equal password)" class="form-control" required>
            </div>
            <button type="submit" class="btn btn-success" value="Register">Register</button>
          </form>        
    </div>
    <script src="http://getbootstrap.com/assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>