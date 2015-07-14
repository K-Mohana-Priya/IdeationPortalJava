<%@page import = "java.sql.*" %>
<%@page import = "Beans.*" %>
<%@page import = "dao.*" %>
<%
response.setContentType("text/html");
userbean user=(userbean)session.getAttribute("user");
String id=user.getUser_id();
String count=new Integer(new notificationsdao().getNotificationCount(id)).toString();
out.write(count);
%>