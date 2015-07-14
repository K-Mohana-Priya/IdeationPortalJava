<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<title>CSC Idea Portal</title>

</head>
<body>
 <c:set var="review" scope="application" value="${sessionScope.user.panelist_flag }"></c:set>

 <c:set var="manager" scope="application" value="${sessionScope.pos }"></c:set>
 <div class="container-fluid">
 <a  href="#" class="navbar-brand nav-fixed" aria-hidden="true" ></a>
  <ul class="nav nav-tabs">
	<li class="active"><a data-toggle="tab" href="#public">Public Ideas</a></li>
  <c:if test="${manager eq '0' }">
    <li><a data-toggle="tab" href="#myidea">My Ideas</a></li>
	<li><a data-toggle="tab" href="#add">Add New Ideas</a></li>
	</c:if>
    <c:if test="${manager eq '1' }">
    <li ><a data-toggle="tab" href="#approved">Approve Ideas</a></li>
    </c:if>
     <c:if test="${review eq true }">
    <li><a data-toggle="tab" href="#review">Review Ideas</a></li>
    </c:if>
  </ul>
  <br/>
  <br/>
  <div class="tab-content">
    <div id="public" class="tab-pane fade in active">
    <jsp:include page="publicideas.jsp"></jsp:include>
    </div>
    <div id="approved" class="tab-pane fade">
    <jsp:include page="approveideas.jsp"></jsp:include>
    </div>
    <div id="myidea" class="tab-pane fade">
    <jsp:include page="myideas.jsp"></jsp:include>
    </div>
    <div id="review" class="tab-pane fade">
      <jsp:include page="reviewideas.jsp"></jsp:include>
   </div>
    <div id="add" class="tab-pane fade">
      <jsp:include page="addnewideas.jsp"></jsp:include>
   </div>
  </div>
</div>
</body>
</html>
