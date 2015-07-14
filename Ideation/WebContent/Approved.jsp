<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
<div class="container">
  <div class="jumbotron">
    <h1>Approved Idea</h1>
    <p>Idea was approved by the manager.</p>
  </div>
   <c:set var="body" value="${sessionScope.idea.idea_body }"/>
   <c:set var="i_user_id" value="${sessionScope.idea.user_id }"/>
   <c:set var="u_user_id" value="${sessionScope.user.user_id }"/>
   <c:if test="${i_user_id eq u_user_id }">
  <form action="deleteidea" method="post" id="todelete">
  <input type="hidden" id="ticked" name="ticked" value="" />
  <div align="right"><button type="button" class="btn btn-default"  onclick="addDeleteHandler(this)" value="${sessionScope.idea.idea_id }" ><span class="glyphicon glyphicon-trash"></span></button></div>
  </form>
  </c:if>
  <strong>Title: </strong>
  <c:set var="title" value="${sessionScope.idea.title }"/>
  <pre><c:out value="${ title}"></c:out></pre>
  <br/>
  <br/>
  <strong>Idea Content: </strong>
  <br/>
  <pre><c:out value="${body}"></c:out></pre>
   <br/>
  <br/>
   <strong>Attached File: </strong>
  <br/>
  <c:forEach var="res" items="${sessionScope.attachments }">
  <c:if test="${ res ne '' }">
   <a href="submitidea?fileName=${res }">${res }</a><br/>
   </c:if>
</c:forEach>
  <br/>
  <hr/>
  <sql:query dataSource="${myidea}" var="mainresult" >
	select * from approval where idea_id=?
	<sql:param value="${sessionScope.idea.idea_id}"></sql:param> 
	</sql:query>
	<c:forEach var="res" items="${mainresult.rows}">
	<sql:query dataSource="${myidea}" var="result" >
	select * from user where user_id=?
	<sql:param value="${res.approving_user_id}"></sql:param> 
	</sql:query>
	<c:forEach var="res1" items="${result.rows}"><br/>
	  <strong>Approved by :</strong><br/>
	 <pre>${res1.user_name } </pre><br/>
	</c:forEach>
	<br/>
	<strong>Rating : </strong><br/><pre>${res.rating }</pre>
	<br/>
	<strong>Comment : </strong>
	<pre>${res.comments } </pre>
	 <br/>
  </c:forEach>
  </div>
  </body>
</html>