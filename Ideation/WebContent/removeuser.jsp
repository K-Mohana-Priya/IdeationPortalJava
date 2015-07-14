<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
  <script src="refreshmessage.js"></script>
<title>Remove User</title>
<style>
.col-centered{
    margin: 0 auto;
    float: none;
}
.centered{
text-align:center;
}
.menu{
background-color:#f0f0f0;
}
.menu a{
margin-right:0px;
}
.menu a:link,
.menu a:visited
{
	color:black;
	text-decoration:none;
}
	
.menu a:hover,
.menu a:active{
color:black;
	text-decoration:none;
background-color:#c0c0c0;
}
</style>
<script>
$( document ).ready(function() {
    var heights = $(".equaliseheight").map(function() {
        return $(this).height();
    }).get(),

    maxHeight = Math.max.apply(null, heights);

    $(".equaliseheight").height(maxHeight);
});
</script>
</head>
<body>
<div class="container-fluid">
<div class="row" style="background-color:#663399;height:50pt;padding-top:15px">
 <div class="col-sm-3 col-md-3 col-lg-5" style="color:#ffffff;font-size:30pt;font-weight:bold;text-indent:10pt;"><p>CSC</p></div>
 <div class="col-sm-3 col-md-3 col-lg-2 "  id="welcome" style="color:#ffffff;font-size:14pt;padding:10px;margin:5pt"><p>Welcome ${sessionScope.user.user_name }</p></div>
 <div class="col-sm-2 col-md-2 col-lg-1 " align="right" id="message"><a href="adminmessage.jsp" style=" text-decoration:none"><button type="button" class="btn btn-info"><span class="glyphicon glyphicon-envelope" aria-hidden="true"></span> Messages <span id="msg" class="badge"></span></button></a></div>
 <div class="col-sm-2 col-md-2 col-lg-2 " align="right" id="notification"><a href="adminnotification.jsp" style=" text-decoration:none"><button type="button" class="btn btn-info"><span class="glyphicon glyphicon-bullhorn" aria-hidden="true"></span>
  Notification <span id="noti" class="badge"></span></button>
  </div>
 <div class="col-sm-1 col-md-1 col-lg-1 " align="right" id="logout"><a href="Login.jsp" style=" text-decoration:none"><button type="button" class="btn btn-info" onclick="disableBack()"><span class="glyphicon glyphicon-log-out" aria-hidden="true"></span> Logout</button></a></div></div>
</div>

<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li><a href="adminhome.jsp">Home</a></li>
        <li class="active"><a href="adduser.jsp">Manage Users</a></li>
        <li><a href="viewgroups.jsp">Manage Groups</a></li>
        <li><a href="forums/list.page">Manage Forum</a></li>
        <c:if test="${sessionScope.roleid eq 1 }">
        <li><a href='viewadmins.jsp'>Manage Admins</a></li>
        </c:if>
        </ul>
    </div>
  </div>
</nav>
<div class='row'><div class='col-lg-2 col-md-2 col-sm-3 menu equaliseheight'>
<a href="adminhome.jsp" class='col-lg-12 col-md-12 col-sm-12'>Add Single User</a><br>
<a href="addusers.jsp" class='col-lg-12 col-md-12 col-sm-12'>Add Multiple Users</a><br>
<a href="editusers.jsp" class='col-lg-12 col-md-12 col-sm-12'>Edit Users</a><br>
<a href="removeusers.jsp" class='col-lg-12 col-md-12 col-sm-12' style="background-color:#dfdfdf">Remove Users</a><br>
</div>
<div class="col-lg-10 col-md-10 col-sm-9 equaliseheight">
<h4>User details:</h4>
<table>
<tr><td style="text-align:right;color:blue;">User Id :</td><td>${requestScope.ruser.getUser_id()}</td></tr>
<tr><td style="text-align:right;color:blue;">User Name :</td><td>${requestScope.ruser.getUser_name()} </td></tr>
<tr><td style="text-align:right;color:blue;">Email :</td><td>${requestScope.ruser.getEmail()}</td></tr>
<tr><td style="text-align:right;color:blue;">Mobile No :</td><td>${requestScope.ruser.getMobile_no()}</td></tr>
<tr><td style="text-align:right;color:blue;">Date Joined :</td><td>${requestScope.ruser.getDate_joined()}</td></tr>
<tr><td style="text-align:right;color:blue;">Level :</td><td>${requestScope.ruser.getLevel()}</td></tr>
<tr><td style="text-align:right;color:blue;">Layer :</td><td>${requestScope.ruser.getLayer()}</td></tr>
<tr><td style="text-align:right;color:blue;">Manager Id 1 :</td><td>${requestScope.ruser.getManager_id_1()}</td></tr>
<tr><td style="text-align:right;color:blue;">Manager Id 2 :</td><td>${requestScope.ruser.getManager_id_2()}</td></tr>
<tr><td style="text-align:right;color:blue;">No of Ideas :</td><td>${requestScope.ruser.getNo_of_ideas()}</td></tr>
</table><br><br>
<p style="color:red">Are you sure you want to remove this user?</p>

<form role="form" id="reg" action="removeusercontroller" method="get">
    <input type="hidden" value="${requestScope.ruser.getUser_id()}" name="uid" id="uid"/> 
<button type="submit" class="btn btn-default">Remove</button>
<a class="btn btn-default" href="removeusers.jsp">Cancel</a>
</form>
</div>
</div>
</body>
</html>