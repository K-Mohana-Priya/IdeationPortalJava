<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CSC Login</title>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script>
//Preload Images
img1 = new Image(16, 16);  
img1.src="images/spinner.gif";
 
img2 = new Image(220, 19);  
img2.src="images/ajax-loader.gif";

history.pushState(null,null,"Login.jsp");
window.addEventListener('popstate',function(event){
	history.pushState(null,null,"Login.jsp");
});

function verify() {
	but = document.getElementById("submit");
	resp_txt = document.getElementById("login_response"); 
	email = document.getElementById("email"); 
	password = document.getElementById("password");
	
	if(email.value != null && password.value != null)
	{
	but.disabled=true;
	document.getElementById("ajax_loading").style.visibility="visible";
	var request=null;
	if (window.XMLHttpRequest) {
	request = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
	request = new ActiveXObject("Microsoft.XMLHTTP");
	}

	   if(request)
	   {
	    var url="Authenticate.jsp";
	    url+="?email="+email.value+ '&' +"password="+password.value;
	   
	    request.open("POST",url);
	    request.onreadystatechange = function()
	    {
	      
	   if(request.readyState==4)
	      {
	        var resp=request.responseText;
	        if(resp.indexOf("USER")!=-1){
		          window.location.href="user_profile.jsp";
		        }
	        else if(resp.indexOf("ADMIN")!=-1){
		          window.location.href="adminhome.jsp";
		        }
	        else{
	        	resp_txt.setAttribute("class", "alert alert-danger");
	        	resp_txt.innerHTML=resp;
	        	but.disabled=false;
	        	document.getElementById("ajax_loading").style.visibility="hidden";
	        	}
	      }

	   }
	   request.send(null);
	   
	   }
	   else
	   {
	    alert("Your browser doesn't support AJAX !");
	   }
	   }
	else{
		resp_txt.setAttribute("class", "alert alert-danger");
    	resp_txt.innerHTML="Please fill all details.";
    	but.disabled=false;
    	document.getElementById("ajax_loading").style.visibility="hidden";
	}
	return false;
	}
</script>
<style>
input[type="text"]:focus,
input[type="password"]:focus,
input[type="radio"]:focus,
input[type="checkbox"]:focus,
input[type="submit"]:focus{
  outline: none;
  -moz-box-shadow: inset 0 1px 2px rgba(0,0,0,0.3);
  -webkit-box-shadow: inset 0 1px 2px rgba(0,0,0,0.3);
  box-shadow: inset 0 1px 2px rgba(0,0,0,0.3);
}
input[type="submit"]:hover{
background-color: #0000c9;
color: #fff;
}
input[type="submit"]:active{
background-color: #0000aa;
color: #fff;
}
input[type="submit"]{
background-color: #0000bb;
color: #fff;
}
#login_form{
font-family:'Trebuchet MS', Helvetica, sans-serif;
background-color:#F0F0F0 ;
padding:10pt;
width:250pt;
margin:auto;
heigth:50pt;
 -moz-border-radius: 2px;
  -webkit-border-radius: 2px;
  border-radius: 2px;
  -moz-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
  -webkit-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
  box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
}
#div1{
font-family:'Comic Sans MS', cursive, sans-serif; 
margin:30pt;
padding-top:10pt";
}
 .signin-card input[type=password],
  .signin-card input[type=text],
  .signin-card input[type=submit] {
  height: 10%;
  width: 80%;
  display: block;
  z-index: 1;
  position: relative;
  -moz-box-sizing: border-box;
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
  }
</style>
</head>
<body >
<div>
<div class="row" style="background-color:#663399;height:50pt;padding-top:15px">
 <div class="col-sm-3 col-md-3 col-lg-5" style="color:#ffffff;font-size:30pt;font-weight:bold;text-indent:10pt;"><p>CSC</p></div>
  </div>
<br>
<br><br>
<div id="login_form" align="center" class="signin-card">
	 <form role="form" id="login" onsubmit="return verify()">
	 <input type="hidden" name="action" value="user_login">
      <input type="hidden" name="module" value="login">
	 
	 <div class="form-group">
	 <br><br>
		<input type="text" id="email" name="email" placeholder="email">
		</div>
		 <div class="form-group"> 
		<input type="password" placeholder="password" id="password" name="password">
		</div>
		<div class="checkbox">
		<label><input type="checkbox"  name="remember">&nbsp;Remember me</label>
		</div>
		<div id="login_response">
		<!-- error message -->
		</div>
 		
		<button name="Login" id="submit" class="btn btn-primary btn-md" type="submit" ">Login</button>
			<div id="ajax_loading" style="visibility:hidden">
			<img align="absmiddle" src="images/spinner.gif">&nbsp;Processing...
			</div>
 
	</form>
 </div>
 </div>

</body>

</html>

