<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta name="viewport" content="width=device-width,initial-scale=1">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<title>CSC Idea Portal</title>

<script>
function addRowHandler() {
    var table = document.getElementsByTagName("table");
    for(var j=0;j<table.length;j++){
cells = table[j].getElementsByTagName('tr');

 for (var i=0; i<cells.length; i++){
 cells[i].onclick = function(){
	 var myrow=this;
	 var selected = document.getElementById("selectedidea");
 	var ideaid = myrow.getElementsByTagName("input");
 	selected.value=ideaid[0].value;
	document.forms["submitidea"].submit();
}
 }
 }
}
 function init() { addRowHandler(); }
 window.onload = init;   
</script>
</head>
<body>
<form id="submitidea" action="extractidea" method="post">
 <input type="hidden" name="selectedidea" value="" id="selectedidea"/>
<div class="container">
  <div class="jumbotron">
    <h1>Approve Ideas</h1>
    <p>Idea submitted by CSC-ites are to be aprroved by you.</p>
  </div>
  <sql:setDataSource var="myidea"
	url="jdbc:mysql://localhost:8888/ideation"
	driver="com.mysql.jdbc.Driver"
	user="myuser" password="tina" />
	
	<sql:query dataSource="${myidea}" var="main_result" >
	select idea_id from approval where approving_user_id=?
	<sql:param value="${sessionScope.user.user_id}"></sql:param>
	</sql:query>
	
	
	<fieldset><strong>Status:</strong>Pending Approval</fieldset>
	<table class="table table-hover">
	 <thead><tr><th>Title</th><th>Submitted By</th><th>Submitted On</th></tr></thead><tbody>
	<c:forEach var="res_m" items="${main_result.rows}">
	<sql:query dataSource="${myidea}" var="result" >
	select * from idea where idea_id=? and status="Pending Approval"
	<sql:param value="${res_m.idea_id}"></sql:param>
	</sql:query>
	<c:if test="${result.rowCount != 0 }">
	<c:forEach var="res" items="${result.rows}">
	 <tr><td>${res.title }</td><td>${res.user_id }</td><td>${res.date_added }</td><td><input type="hidden" id="${res.idea_id}" value="${res.idea_id}"/></td></tr>
	</c:forEach>
	</c:if>
	</c:forEach>
	</tbody></table>
	<br/>
	
	
	<fieldset><strong>Status:</strong>Approved</fieldset>
	<table class="table table-hover">
	 <thead><tr><th>Title</th><th>Submitted By</th><th>Submitted On</th><th>Approved On</th></tr></thead> <tbody>
	<c:forEach var="res_m" items="${main_result.rows}">
	<sql:query dataSource="${myidea}" var="result" >
	select * from idea where idea_id=? and status="Approved"
	<sql:param value="${res_m.idea_id}"></sql:param>
	</sql:query>
	<c:if test="${result.rowCount != 0 }">
	<c:forEach var="res" items="${result.rows}">
	<tr><td>${res.title }</td><td>${res.user_id }</td><td>${res.date_added }</td><td>${res.date_approved }</td><td><input type="hidden" id="${res.idea_id}" value="${res.idea_id}"/></td></tr>
	</c:forEach>
	</c:if>
	</c:forEach>
	 </tbody></table>
	<br/>
	
	
	<fieldset><strong>Status:</strong>Rejected</fieldset>
	<table class="table table-hover">
	 <thead><tr><th>Title</th><th>Submitted By</th><th>Submitted On</th><th>Rejected On</th></tr></thead> <tbody>
	<c:forEach var="res_m" items="${main_result.rows}">
	<sql:query dataSource="${myidea}" var="result" >
	select * from idea where idea_id=? and status="Rejected"
	<sql:param value="${res_m.idea_id}"></sql:param>
	</sql:query>
	<c:if test="${result.rowCount != 0 }">
	<c:forEach var="res" items="${result.rows}">
	 <tr><td>${res.title }</td><td>${res.user_id }</td><td>${res.date_added }</td><td>${res.date_approved }</td><td><input type="hidden" id="${res.idea_id}" value="${res.idea_id}"/></td></tr>
	</c:forEach>
	</c:if>
	</c:forEach>
	</tbody></table>
	<br/>
</div>
</form>
</body>
</html>