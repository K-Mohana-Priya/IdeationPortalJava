<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<title>Messages</title>
<script>
function addDeleteHandler(elt) {
	 var selected= document.getElementById("ticked");
        selected.value=elt.value;
        document.forms["todelete"].submit();
}  
</script>
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
<script src="refreshmessage.js"></script>
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
<sql:setDataSource var="myidea"
	url="jdbc:mysql://localhost:8888/ideation"
	driver="com.mysql.jdbc.Driver"
	user="myuser" password="tina" />

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
        <li class="active"><a href="adminhome.jsp">Home</a></li>
        <li><a href="adduser.jsp">Manage Users</a></li>
        <li><a href="creategroup.jsp">Manage Groups</a></li>
        <li><a href="forums/list.page">Manage Forum</a></li>
        <c:if test="${sessionScope.roleid eq 1 }">
        <li><a href='viewadmins.jsp'>Manage Admins</a></li>
        </c:if>
        </ul>
    </div>
  </div>
</nav>
<div class='row'><div class='col-lg-2 col-md-2 col-sm-3 menu equaliseheight'>
<a href="adminmessage.jsp" class='col-lg-12 col-md-12 col-sm-12' style="background-color:#dfdfdf">Send Messages</a><br>
<a href="adminnotification.jsp" class='col-lg-12 col-md-12 col-sm-12'>View Notifications</a><br>
</div>
<div class="col-lg-10 col-md-10 col-sm-9 equaliseheight">	
  
  <form action="deletemsg" method="post" id="todelete">
  <input type="hidden" id="ticked" name="ticked" value="" />
  <h2>Messages</h2>
  <table class="table table-hover">
  <thead><tr><th>Sender</th><th>Message</th><th>Delete</th></tr></thead>
  <tbody>
   <sql:query dataSource="${myidea}" var="result" >
	select * from message where user_id=?
	<sql:param value="${sessionScope.user.user_id}"></sql:param> 
	</sql:query>
	
	<c:forEach var="res1" items="${result.rows}" >
	<sql:query dataSource="${myidea}" var="subres" >
	select user_name from user where user_id=? 
	<sql:param value="${res1.sending_user_id}"></sql:param> 
	</sql:query>
	<tr>
	<c:forEach var="res2" items="${subres.rows}">
	<td>${res2.user_name }</td>
	</c:forEach>
	<td>${res1.message_data }</td><td><button type="button" class="btn btn-default"  onclick="addDeleteHandler(this)" value="${res1.message_id }" ><span class="glyphicon glyphicon-trash"></span></button></td></tr>
	</c:forEach>
	
	</tbody>
	</table>
  </form>
  <div>
  <br/>
  <br/>
  <form action="sendmes" method="post">
  <input type="hidden" name="user_id" value="${sessionScope.user.user_id }"/>
  <p><strong>Send Message:</strong></p>
  <div class="form-group">
  <label for="to">To:</label>
  <input type="text" class="form-control" placeholder="mail id" name="mail" id="mail"/>
</div>
 <div class="form-group">
  <label for="data">Message:</label>
  <textarea class="form-control" rows="5" name="data" id="data"></textarea>
</div>
<div align="center">
  <button type="submit" class="btn btn-primary" name="submit">Send..</button>
  <button type="reset" class="btn btn-primary" name="cancel">Cancel</button>
  </div>
  </form>
  </div>
  </div>
</body>
</html>