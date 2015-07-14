<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Idea pending for Approval</title>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
  </head>
  <body>
<sql:setDataSource var="myidea"
	url="jdbc:mysql://localhost:8888/ideation"
	driver="com.mysql.jdbc.Driver"
	user="myuser" password="tina" />
<div class="container">
  <div class="jumbotron">
    <h1>Analysis of submitted Idea</h1>
    <p>Idea which have been submitted for approval are need to be analysed by you.</p>
  </div>
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
  <sql:query dataSource="${myidea}" var="mainresult" >
	select * from approval where idea_id=?
	<sql:param value="${sessionScope.idea.idea_id}"></sql:param> 
	</sql:query>
	<c:forEach var="res" items="${mainresult.rows}">
	<sql:query dataSource="${myidea}" var="result" >
	select * from user where user_id=?
	<sql:param value="${res.approving_user_id}"></sql:param> 
	</sql:query>
	<c:forEach var="res1" items="${result.rows}">
	  <strong>Approver :	</strong><br/>
	  <pre>${res1.user_name }  </pre><br/>
	</c:forEach>
	 <br/>
  </c:forEach>
  <form action="approving" method="post">
  <input type="hidden" name="ideaid" value="${sessionScope.idea.idea_id}"/>
  <div class="form-group">
   <label for="rating">Rating(Higher rating for good ideas):</label>
   <br/>
  <input type="radio" value="1" name="rating"/>1&nbsp;
  <input type="radio" value="2" name="rating"/>2&nbsp;
  <input type="radio" value="3" name="rating"/>3&nbsp;
  <input type="radio" value="4" name="rating"/>4&nbsp;
  <input type="radio" value="5" name="rating"/>5&nbsp;
  <br/>
 <strong>Comment:</strong>
  Enter your comments here. If the idea is to be rejected mention the reason. The suggestions or improvements also need to be mentioned here.
  <textarea class="form-control" rows="5" name="comment" id="comment"></textarea>
</div>
<br/>
   <div align=center>
  <input type="radio" value="accept" name="saveas"/>Accept&nbsp;&nbsp;&nbsp;
  <input type="radio" value="reject" name="saveas"/>Reject
  <br/>
  <br/>
</div>
  <div align="center">
<button type="submit" class="btn btn-primary" name="submit">Save</button>
<button type="reset" class="btn btn-primary" name="clear">Cancel</button>
</div>
<br/><br/>
  </form> 
</body>
</html>