<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reviewed Idea</title>
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
    <h1>Reviewed Idea</h1>
    <p>Idea was reviewed by the panelist.</p>
  </div>
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
   <c:set var="body" value="${sessionScope.idea.idea_body }"/>
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
  <sql:query dataSource="${myidea}" var="result" >
	select * from user 
	</sql:query>
	<div class="form-group">
	<c:forEach var="res" items="${result.rows}">
	<c:set var="bool" value="${res.panelist_flag }"/>
	<c:if test="${ bool == true}">
	<strong>Panelist :</strong>${res.user_name }<br/>
	<sql:query dataSource="${myidea}" var="subresult" >
	select * from review where reviewer_user_id=?
	<sql:param value="${res.user_id}"></sql:param>
	</sql:query>
	 <c:if test="${result.rowCount != 0 }">
	 <c:forEach var="res1" items="${subresult.rows}">
	 <strong>Rating:</strong><br/>
	 <pre>${res1.rating }</pre>
	 <br/>
	 <strong>Comments:</strong>
	 <br/>
	<pre>${res1.comments }</pre> 
	 <hr/>
	  </c:forEach>
	 </c:if>
	 <c:if test="${result.rowCount == 0 }">
	<pre>Not Reviewed yet.</pre> 
	 <br/>
	 </c:if>
	</c:if>
	</c:forEach>
	</p>
	</div>
  </div>
</body>
</html>