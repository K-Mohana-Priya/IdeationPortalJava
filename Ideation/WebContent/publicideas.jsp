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
    <h1>Public Ideas</h1>
    <p>Ideas that have been reviewed by the panelists are shown here. Click on the idea to known about the idea in detail.</p>
  </div>
  <sql:setDataSource var="myidea"
	url="jdbc:mysql://localhost:8888/ideation"
	driver="com.mysql.jdbc.Driver"
	user="myuser" password="tina" />
	
	<sql:query dataSource="${myidea}" var="result" >
	select * from idea where status="Reviewed By Panel"
	</sql:query>
	<c:if test="${result.rowCount != 0 }">
	<fieldset><strong>Ideas after review</strong></fieldset>
	<table class="table table-hover">
	 <thead><tr><th>Title</th><th>Submitted By</th><th>Submitted On</th><th>Reviewed On</th></tr></thead>
	<c:forEach var="res" items="${result.rows}">
	 <tbody><tr><td>${res.title }</td><td>${res.user_id }</td><td>${res.date_added }</td><td>${res.date_approved }</td><td><input type="hidden" id="${res.idea_id}" value="${res.idea_id}"/></td></tr></tbody>
	</c:forEach>
	</table>
	</c:if> 
</div>
</form>
</body>
</html>