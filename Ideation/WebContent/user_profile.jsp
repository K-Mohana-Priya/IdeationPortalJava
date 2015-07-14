<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<title>CSC Idea Portal</title>
<script>
function count(){
	var tt=setInterval(update,2000);
}
function update(){
	noti = document.getElementById("noti");
	msg = document.getElementById("msg");
	var xmlDoc,txt,x;
	var request=null;
	if (window.XMLHttpRequest) {
	request = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
	request = new ActiveXObject("Microsoft.XMLHTTP");
	}

	   if(request)
	   {
	    var url="Message_Notify";
	   
	    request.onreadystatechange = function()
	    {
	      
	   if(request.readyState==4)
	      {
		  xmlDoc=request.responseXML;
		    txt="";
		    x=xmlDoc.getElementsByTagName("message");
		      txt= x[0].childNodes[0].nodeValue;
		      if(txt != 0)
		    document.getElementById("msg").innerHTML=txt;
		    txt="";
		    x=xmlDoc.getElementsByTagName("notify");
		      txt= x[0].childNodes[0].nodeValue;
		    document.getElementById("noti").innerHTML=txt;
	      }

	   }

	    request.open("GET",url,true);
	       request.send(null);
 }
}
  
 function init() { 
	 $("#logout").onclick(function(){
		   history.pushState(null, null, 'Login.jsp');
		   window.addEventListener('popstate', function(event) {
		   history.pushState(null, null, 'Login.jsp');
		   });
	    	document.location="logoutcontroller";
	  
	    }); 
	 
	 count(); }
 
 window.onload = init(); 
 
 /*function disableBack(){
 history.pushState(null, null, 'Login.jsp');
 window.addEventListener('popstate', function(event) {
 history.pushState(null, null, 'Login.jsp');
 });
 sessionInvalidate();*/
 
</script>
<style>
.scrollable-menu {
    height: auto;
    max-height: 180px;
    overflow-x: hidden;
}
.scrollable-menu::-webkit-scrollbar {
    -webkit-appearance: none;
    width: 4px;        
}    
.scrollable-menu::-webkit-scrollbar-thumb {
    border-radius: 3px;
    background-color: lightgray;
    -webkit-box-shadow: 0 0 1px rgba(255,255,255,.75);        
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
</head>
<body>
 <sql:setDataSource var="myidea"
	url="jdbc:mysql://localhost:8888/ideation"
	driver="com.mysql.jdbc.Driver"
	user="myuser" password="tina" />
	<sql:query dataSource="${myidea}" var="result" >
	select notification_data from notifications where user_id=?
	<sql:param value="${sessionScope.user.user_id}"></sql:param>
	</sql:query>
<div class="container-fluid">
<div class="row" style="background-color:#663399;height:50pt;padding-top:15px">
 <div class="col-sm-3 col-md-3 col-lg-5" style="color:#ffffff;font-size:30pt;font-weight:bold;text-indent:10pt;"><p>CSC</p></div>
 <div class="col-sm-3 col-md-3 col-lg-2 "  id="welcome" style="color:#ffffff;font-size:14pt;padding:10px;margin:5pt"><p>Welcome ${sessionScope.user.user_name }</p></div>
 <div class="col-sm-2 col-md-2 col-lg-1 " align="right" id="message"><a href="user_profile.jsp" style=" text-decoration:none"><button type="button" class="btn btn-info"><span class="glyphicon glyphicon-envelope" aria-hidden="true"></span> Messages <span id="msg" class="badge"></span></button></a></div>
 <div class="col-sm-2 col-md-2 col-lg-2 dropdown" align="right" id="notification"><button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown" ><span class="glyphicon glyphicon-bullhorn" aria-hidden="true"></span>
  Notification <span id="noti" class="badge"></span></button>
  <ul class="dropdown-menu scrollable-menu" role="menu">
  <c:forEach var="res" items="${result.rows}">
    <li><a href="#">${res.notification_data }</a></li>
    </c:forEach>
  </ul></div>
 <div class="col-sm-1 col-md-1 col-lg-1 " align="right" id="logout"><a href="Login.jsp" style=" text-decoration:none"><button type="button" class="btn btn-info" onclick="disableBack()"><span class="glyphicon glyphicon-log-out" aria-hidden="true"></span> Logout</button></a></div>
 </div>
 </div>
 <c:set var="role" scope="application" value="${sessionScope.roleid }"></c:set>

 <c:set var="manager" scope="application" value="${sessionScope.pos }"></c:set>
 <div class="container-fluid">
 <a  href="user_profile.jsp" class="navbar-brand nav-fixed" aria-hidden="true" > Ideation Portal </a>
  <ul class="nav nav-tabs">
    <li class="active"><a data-toggle="tab" href="#home">Home</a></li>
	<li><a data-toggle="tab" href="#ideas">Ideas</a></li>
	<li><a data-toggle="tab" href="#forum">Forum</a></li>
    </ul>
    <br/>
  <div class="tab-content">
    <div id="home" class="tab-pane fade in active">
    <jsp:include page="home.jsp"></jsp:include>
    </div>
    <div id="ideas" class="tab-pane fade">
    <jsp:include page="userhome.jsp"></jsp:include>
    </div>
    <div id="forum" class="tab-pane fade">
    <jsp:include page="aboutforum.jsp"></jsp:include>
    </div>
    </div>
    <br/>
    <br/>
  
</div>

</body>
</html>
