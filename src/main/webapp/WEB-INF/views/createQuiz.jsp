<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script>
function execute(a)
{
	console.log("hey there!")
	console.log(a);
	var quest = document.createElement('div');
	quest.setAttribute('id','questions');
	var row = document.createElement('tr');
	var rowdata = document.createElement('td');
	var inputQ = document.createElement('input');
	inputQ.setAttribute('type', 'text');
	inputQ.setAttribute('id','quest1');
	inputQ.setAttribute('style', 'width:500px; height:50px');
	rowdata.appendChild(inputQ);
	row.appendChild(rowdata);
	quest.appendChild(row);
	//Creating options a & b
	var rowOption1 = document.createElement('tr');
	var rowOptData = document.createElement('td');
	var divA = document.createElement('div');
	divA.setAttribute('style','width:200px;display:inline-block')
	var inpA = document.createElement('input');
	inpA.setAttribute('type','text');
	//inpA.setAttribute('id','text');
	inpA.setAttribute('style','width:100px;height:20px');
	divA.appendChild(inpA);
	rowOptData.appendChild(divA);
	var divB = document.createElement('div');
	divB.setAttribute('style','width:200px;display:inline-block')
	var inpB = document.createElement('input');
	inpB.setAttribute('type','text');
	//inpA.setAttribute('id','text');
	inpB.setAttribute('style','width:100px;height:20px');
	divB.appendChild(inpB);
	rowOptData.appendChild(divB);
	rowOption1.appendChild(rowOptData);
	quest.appendChild(rowOption1);
	//repeating for options c & d
	var rowOption2 = document.createElement('tr');
	var rowOptData2 = document.createElement('td');
	var divC = document.createElement('div');
	divC.setAttribute('style','width:200px;display:inline-block')
	var inpC = document.createElement('input');
	inpC.setAttribute('type','text');
	inpC.setAttribute('style','width:100px;height:20px');
	divC.appendChild(inpC);
	rowOptData2.appendChild(divC);
	var divD = document.createElement('div');
	divD.setAttribute('style','width:200px;display:inline-block')
	var inpD = document.createElement('input');
	inpD.setAttribute('type','text');
	//inpA.setAttribute('id','text');
	inpD.setAttribute('style','width:100px;height:20px');
	divD.appendChild(inpD);
	rowOptData2.appendChild(divD);
	rowOption2.appendChild(rowOptData2);
	quest.appendChild(rowOption2);
	
	document.getElementById('quizQues').appendChild(quest);
}
</script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">

<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
</head>
<body>
 <div class="container">
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
              <li><a href="/homepageCM">Home</a></li>
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
      <form class="form-signin" method="post" action="saveContentQuiz?courseId=${courseId}" role="form">
      <input type="text" name="contentId" placeholder="Quiz Name/Id"  class="form-control" required style="width:200px"/>
      <button name="buttonExecute"  type="submit" value="Execute">Start</button>
      </form>
      <!-- onclick="execute(document.getElementById('quesCount').value)" -->
 
          <!--  
<table class="table" id="quizQues">
    <tbody>
 
        <tr> <td><div>
        <input type="text" id="ques1" style="width:500px; height:50px"/></div>
        </td>
        </tr>
        <tr>
            <td><div style="width:200px;display:inline-block">
                a.<input type="text" id="ques1op1" style="width:50px;height:20px"/>
                </div>
            <div style="width:80px; display:inline-block">
                b.<input type="text" id="ques1op2" style="width:50px;height:20px"/>
                </div>
            </td>
        </tr>
        <tr>
            <td><div style="width:200px;display:inline-block">
                c.<input type="text" id="ques1op3" style="width:50px;height:20px"/>                    </div>
            <div style="width:80px; display:inline-block">
                d.<input type="text" id="ques1op4" style="width:50px;height:20px"/></div>
            </td>
        </tr>
        <tr>
           <td>
               Answer.<input type="text" id="ques1op4" style="width:50px;height:20px"/>
            </td>
        </tr> 
    </tbody>
</table>
-->
</div> <!-- container -->

</body>
</html>