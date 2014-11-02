<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 
<html>
<body>
	<h1>Add Employee</h1>
 
	<form:form method="post"  modelAttribute="userEntity"  action="save">
		<table>
    <tr>
        <td><form:label path="firstName">First Name</form:label></td>
        <td><form:input path="firstName" /></td>
    </tr>
    <tr>
        <td><form:label path="lastName">Last Name</form:label></td>
        <td><form:input path="lastName" /></td>
    </tr>
    <tr>
        <td><form:label path="emailId">Email Address</form:label></td>
        <td><form:input path="emailId" /></td>
    </tr>
    <tr>
        <td><form:label path="phoneNumber">Phone Number</form:label></td>
        <td><form:input path="phoneNumber" /></td>
    </tr>
     <tr>
     	<td><form:label path="encryptedPassword">PassWord</form:label></td>
        <td><form:input type="password" path ="encryptedPassword"/></td>
     </tr>
     <tr>
    <td>
      	<input type="submit" value="Submit"/>
     </td>
     </tr>
   
</table> 
	</form:form>
</body>
</html>