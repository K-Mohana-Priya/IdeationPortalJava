<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta name="viewport" content="width=device-width,initial-scale=1">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<title>CSC Idea Portal</title>

<script>
function entryCheck() {
    var control =null;
    control=document.getElementById("title").value;
    if(control== ""){
    	alert('Enter the title of the idea');
    	return false;
    }
    control=document.getElementById("description").value;
    if(control== ""){
    	alert('Enter the title of the description');
    	return false;
    }
    control=document.getElementById("target").value;
    if(control == ""){
    	alert('Enter the title of the target');
    	return false;
    }
    control=document.getElementById("impact").value;
    if(control == ""){
    	alert('Enter the title of the impact');
    	return false;
    }
    control=document.getElementById("attachment").value;
    if(control == ""){
    	alert('Enter the title of the attachment');
    	return false;
    }
    return true;
}   
</script>
</head>
<div class="container">
  <div class="jumbotron">
    <h1>Add New Ideas</h1>
    <p>Ideation Portal allows you to put forward the ideas you have come up with. Give the details about your idea with supporting files and submit. Thereby your ideas are captured!!</p>
  </div>
  <form class="form-horizontal" role="form" method="post" action="submitidea" enctype="multipart/form-data" onsubmit="return entryCheck()">
     <div class="form-group">
  <label for="title">Title</label>
  <input type="text" class="form-control" name="title" id="title">
</div>
 <div class="form-group">
  <label for="description">Description:</label>
  <textarea class="form-control" rows="5" name="description" id="description"></textarea>
</div>
 <div class="form-group">
  <label for="target">Target People:</label>
  <textarea class="form-control" rows="5" name="target" id="target"></textarea>
</div>
 <div class="form-group">
  <label for="Impact">Impact:</label>
  <textarea class="form-control" rows="5" name="impact" id="impact"></textarea>
</div>
 <div class="form-group">
  <label for="attachment">Attachment:</label>
  <input type="file" name="attachment" id="attachment" multiple>
</div>
<hr/>
<br/><br/>
    <c:set var="grp" value="${sessionScope.groups}"/>
	<c:set var="groups" value="${ fn:split(grp,' ') }"/>
   <div align=center>
   <c:forEach var="group" items="${groups}">
  <input type="radio" value="${group}" name="submit_for"/>Submit in ${group}&nbsp;&nbsp;
  </c:forEach>
<input type="radio" value="submit_for_review" name="submit_for"/>Submit For Review
</div>
<br/><br/>
<div align="center">
<button type="submit" class="btn btn-primary" name="submit">Submit</button>
<button type="reset" class="btn btn-primary" name="clear">Cancel</button>
</div>
<br/><br/>
  </form>
</div>
</body>
</html>