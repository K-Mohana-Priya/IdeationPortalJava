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
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script>
$( document ).ready(function() {
    var heights = $(".equaliseheight").map(function() {
        return $(this).height();
    }).get(),

    maxHeight = Math.max.apply(null, heights);

    $(".equaliseheight").height(maxHeight);
});
var check1=false;
$(document).ready(function(){
    $("#gname").blur(function(){
    	var request; 
   		var form=document.getElementById("group");
   		var v=form.gname.value;
   var url="validategroupcontroller?val="+v; 
        $.post(url, function(data){
            if(data=="available")
    		{
    			document.getElementById("gname_msg").innerHTML="";
    			check1=true;
    		}	
    		else if(data=="unavailable")
    		{
    		  			 document.getElementById("gname_msg").innerHTML="*Group with given name already exists! ";
    					check1=false;
    		} 
    		else if(data=="empty")
    		{
    			document.getElementById("gname_msg").innerHTML="*Field is mandatory ";
				check1=false;
    		}

        });
    });
});
    function validate()
    {
       if(!check1)
        	return false;
return true;
}
</script>
<title>Create Group</title>
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
        <li><a href="adduser.jsp">Manage Users</a></li>
        <li class="active"><a href="viewgroups.jsp">Manage Groups</a></li>
        <li><a href="forums/list.page">Manage Forum</a></li>
        <c:if test="${sessionScope.roleid eq 1 }">
        <li><a href='viewadmins.jsp'>Manage Admins</a></li>
        </c:if>
        </ul>
    </div>
  </div>
</nav>
<div class='row'><div class='col-lg-2 col-md-2 col-sm-3 menu equaliseheight'>
<a href="viewgroups.jsp" class='col-lg-12 col-md-12 col-sm-12'>View Groups</a><br>
<a href="creategroup.jsp" class='col-lg-12 col-md-12 col-sm-12' style="background-color:#dfdfdf">Create New Group</a><br>
<a href="editgroups.jsp" class='col-lg-12 col-md-12 col-sm-12'>Modify Group</a><br>
<a href="deletegroups.jsp" class='col-lg-12 col-md-12 col-sm-12'>Delete Group</a><br>
</div>
<div class="col-lg-10 col-md-10 col-sm-9 equaliseheight">

<h3>It only takes two simple steps to create a new user group!</h3>
<h4>1. Enter the Group Name.</h4>
<h4>2. Add users to the group.</h4><br><br>
<form role="form" id="group" action="creategroupcontroller" method="get" onsubmit="return validate()">
<div class="col-lg-3 col-md-3 col-sm-4 col-xs-5 col-centered">
      <label for="gname">Group Name:</label>
      <input type="text" class="form-control" id="gname" name="gname" placeholder="Enter Group name">
      <span id="gname_msg" style="color:red"></span><br>
    </div><br><br>

    <div class="centered">
    <button type="submit" class="btn btn-default" id="butt">Create Group</button>
    <a class="btn btn-default" href="adminhome.jsp">Cancel</a>
    </div>
    </form>
</div>
</div>
</body>
</html>