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
<title>CSC Idea Portal</title>
<script>
function addDeleteHandler(elt) {
	 var selected= document.getElementById("ticked");
        selected.value=elt.value;
        document.forms["todelete"].submit();
}  
</script>
</head>
<body>
<sql:setDataSource var="myidea"
	url="jdbc:mysql://localhost:8888/ideation"
	driver="com.mysql.jdbc.Driver"
	user="myuser" password="tina" />
	
<div class="container-fluid">
  <div class="jumbotron">
    <h1>CSC Ideation Portal</h1>
  </div>
  
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
  <input type"text" class="form-control" placeholder="mail id" name="mail" id="mail"/>
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