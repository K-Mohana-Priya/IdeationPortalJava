<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
  <script src="refreshmessage.js"></script>
<title>Admin Home</title>
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
var check3=false;
$(document).ready(function(){
	 $("#uid").blur(function(){
	    	var request; 
	   		var form=document.getElementById("reg");
	   		var v=form.uid.value;
	   		alert(v);
	   var url="validatecontroller?val="+v; 
	        $.post(url, function(data){
	  
	            if(data=="invalid")
	    		{
	    			document.getElementById("fid_msg").innerHTML="";
	    			check3=true;
	    		}	
	    		else if(data=="valid")
	    		{
	    		  			 document.getElementById("fid_msg").innerHTML="*Id already exists!";
	    					check3=false;
	    		} 
	    		else if(data=="empty")
	    			{
	    			document.getElementById("fid_msg").innerHTML="*Field is mandatory!";
					check3=false;
	    			}
	           
	        });
	    });
   
   
});
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
        <li><a href="adduser.jsp">Manage Users</a></li>
        <li><a href="viewgroups.jsp">Manage Groups</a></li>
        <li><a href="forums/list.page">Manage Forum</a></li>
        <li class="active"><a href="viewadmins.jsp">Manage Admins</a></li>
        </ul>
    </div>
  </div>
</nav>
<div class='row'><div class='col-lg-2 col-md-2 col-sm-3 menu equaliseheight'>
<a href="viewadmins.jsp" class='col-lg-12 col-md-12 col-sm-12'>Modify Admins</a><br>
<a href="addadmin.jsp" class='col-lg-12 col-md-12 col-sm-12' style="background-color:#dfdfdf">Add Admin</a><br>

</div>
<div class="col-lg-10 col-md-10 col-sm-9 equaliseheight">
<h3>Enter the Administrator's Details...</h3><br>
<form role="form" id="reg" action="addadmincontroller" method="get" onsubmit="return validate()">
    <div class="col-lg-3 col-md-3 col-sm-4 col-xs-5 col-centered">
      <label for="uid">User Id:</label>
      <input type="text" class="form-control" id="uid" name="uid" placeholder="Enter user id">
      <span id=fid_msg style="color:red"></span><br>
    </div><br>
    <div class="col-lg-3 col-md-3 col-sm-4 col-xs-5 col-centered">
      <label for="uname">User Name:</label>
      <input type="text" class="form-control" id="uname" name="uname" placeholder="Enter user name" onblur="validateName()">
      <span id=fname_msg style="color:red"></span><br>
    </div><br>
    <div class="col-lg-3 col-md-3 col-sm-4 col-xs-5 col-centered">
      <label for="udoj">Date Joined(yyyy-mm-dd):</label>
      <input type="text" class="form-control" id="udoj" name="udoj" placeholder="Enter date joined" onblur="validateDOJ()" >
      <span id=fdoj_msg style="color:red"></span><br>
    </div><br>
    <div class="col-lg-3 col-md-3 col-sm-4 col-xs-5 col-centered">
      <label for="uemail">Email Id:</label>
      <input type="text" class="form-control" id="uemail" name="uemail" placeholder="Enter email id" onblur="validateEmail()">
      <span id=fmail_msg style="color:red"></span><br>
    </div><br>
    <div class="col-lg-3 col-md-3 col-sm-4 col-xs-5 col-centered">
      <label for="umobile">Mobile No:</label>
      <input type="text" class="form-control" id="umobile" name="umobile" placeholder="Enter mobile no" onblur="validateNumber()">
      <span id=fno_msg style="color:red"></span><br>
    </div><br>
    <div class="col-lg-3 col-md-3 col-sm-4 col-xs-5 col-centered">
      <label for="ulevel">Level:</label>
      <input type="text" class="form-control" id="ulevel" name="ulevel" placeholder="Enter user level" onblur="validateLevel()">
      <span id=flevel_msg style="color:red"></span><br>
    </div><br>
    <div class="col-lg-3 col-md-3 col-sm-4 col-xs-5 col-centered">
      <label for="ulayer">Layer:</label>
      <input type="text" class="form-control" id="ulayer" name="ulayer" placeholder="Enter user layer" onblur="validateLayer()">
      <span id=flayer_msg style="color:red"></span><br>
    </div><br>
    
    <div class="centered">
    <button type="submit" class="btn btn-default" id="butt">Add Admin</button>
    <a class="btn btn-default" href="viewadmins.jsp">Cancel</a>
    </div>
  </form>
  <script>
        function validate()
        {
	         if(!validateEmail())
		return false;
             if(!validateNumber())
		return false;
            if(!validateDOJ())
            	return false;
            if(!validateName())
            	return false;
            if(!validateLevel())
            	return false;
            if(!validateLayer())
		return false;
            if(!check3)
            	return false;
	return true;
}
      

   function validateEmail()
{ 
		var form=document.getElementById("reg");
		var exp=/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
		
		if(form.uemail.value.match(exp))
		{
			document.getElementById("fmail_msg").innerHTML="";
			return true;
		}
		if(! form.uemail.value.match(exp))
		{
			document.getElementById("fmail_msg").innerHTML="*Enter a valid Mail id";
			//form.email_id.focus();
			return false;
		}
  }
        
    
function validateNumber()
{ 
		var form=document.getElementById("reg");
		var exp=/^[789]\d{9}$/;
                if(form.umobile.value.match(exp))
		{
			document.getElementById("fno_msg").innerHTML="";
			return true;
		}
		if(! form.umobile.value.match(exp))
		{
			 document.getElementById("fno_msg").innerHTML="*Enter a valid Mobile Number ";
                         return false;
		}
 }
     
function validateDOJ()
{ 
		var form=document.getElementById("reg");
		var exp=/^([0-9]{4})-([0-1][0-9])-([0-3][0-9])$/;
		
		if(form.udoj.value.match(exp))
		{
			document.getElementById("fdoj_msg").innerHTML="";
			return true;
		}
		if(! form.udoj.value.match(exp))
		{
			 document.getElementById("fdoj_msg").innerHTML="*Enter Date in correct format ";
					return false;
		}
}

function validateName()
{
	var form=document.getElementById("reg");
	if(form.uname.value!="")
	{
		document.getElementById("fname_msg").innerHTML="";
		return true;
	}
	else
	{
		 document.getElementById("fname_msg").innerHTML="*Field is mandatory ";
				return false;
	}
}

function validateLevel()
{
	var form=document.getElementById("reg");
	var exp=/^([0-9]+)$/;
	if(form.ulevel.value.match(exp))
	{
		document.getElementById("flevel_msg").innerHTML="";
		return true;
	}
	else
	{
		 document.getElementById("flevel_msg").innerHTML="*Field is mandatory and must be a number";
				return false;
	}
}

function validateLayer()
{
	var form=document.getElementById("reg");
	var exp=/^([0-9]+)$/;
	if(form.ulayer.value.match(exp))
	{
		document.getElementById("flayer_msg").innerHTML="";
		return true;
	}
	else
	{
		 document.getElementById("flayer_msg").innerHTML="*Field is mandatory and must be a number";
				return false;
	}
}

</script>
    
</div>
</div>
</body>
</html>