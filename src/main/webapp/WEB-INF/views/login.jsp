<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

 <html>
 	 <head>
 	 	 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 	 	 <title>JSP Page</title>
 	 </head> 
 	 <body>
 	 <form:form method="post"  modelAttribute="loginEntity"  action="checkLogin">
		<table>
		<tr>
        <td><form:label path="emailId">Email Id</form:label></td>
        <td><form:input path="emailId" /></td>
    	</tr>
    	<tr>
     	<td>Password</td>
        <td><input type="password" name="password" value="" /></td>
     </tr>
     <tr>
     <td>
      	<input type="submit" value="Login"/>
     </td>
  
     </tr>
     <tr>
     <td>
     <a href="register">Create an account</a>
     </td>
     </tr>    
		</table>
		</form:form>
	 </body> 
	 </html>