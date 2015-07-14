<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Idea Pending for review</title>
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
    <h1>Review Idea</h1>
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
  <sql:query dataSource="${myidea}" var="result" >
	select * from user 
	</sql:query>
	 <div class="form-group">
	<strong>Panelist :</strong>
	<c:forEach var="res" items="${result.rows}">
	<br/>
	<c:set var="bool" value="${res.panelist_flag }"/>
	<c:if test="${ bool == true}">
	${res.user_name }
	</c:if>
	</c:forEach>
	</div>
	<br/>
	<sql:query dataSource="${myidea}" var="result" >
	select * from review where idea_id=? and reviewer_user_id=? 
	<sql:param value="${sessionScope.idea.idea_id}"></sql:param> 
	<sql:param value="${sessionScope.user.user_id}"></sql:param>
	</sql:query>
	<c:if test="${result.rowCount == 0 }">
	
  <form action="Reviewing" method="post">
   <div class="form-group">
  <input type="hidden" name="ideaid" value="${sessionScope.idea.idea_id}"/>
   <label for="rating">Rating(Higher rating for good ideas):&nbsp;&nbsp;&nbsp;</label><br/>
  <input type="radio" value="1" name="rating"/>1&nbsp;
  <input type="radio" value="2" name="rating"/>2&nbsp;
  <input type="radio" value="3" name="rating"/>3&nbsp;
  <input type="radio" value="4" name="rating"/>4&nbsp;
  <input type="radio" value="5" name="rating"/>5&nbsp;
  </div>
  <br/>
  <br/>
  <div class="form-group">
  <strong>Comment:</strong><br/>
  Enter your comments here.The suggestions or improvements also need to be mentioned here.
  <textarea class="form-control" rows="5" name="comment" id="comment"></textarea>
  </div>
<br/>

  <div align="center">
<button type="submit" class="btn btn-primary" name="submit">Review</button>
<button type="reset" class="btn btn-primary" name="clear">Cancel</button>
</div>
<br/><br/>
  </form> 
  </c:if>
  <c:if test="${result.rowCount != 0 }">
  <c:forEach var="res" items="${result.rows}">
  <strong>Ratings:</strong>
  <br/>
  <pre>${res.rating }</pre>
  <br/>
  <hr/>
  <strong>Comment:</strong>
  <br/>
  <pre>${res.comments }</pre>
  <br/>
  <div class="jumbotron">
    <p>Idea yet to be reviewed by other panelist.</p>
  </div>
  </c:forEach>
  </c:if>
  </div>
</body>
</html>