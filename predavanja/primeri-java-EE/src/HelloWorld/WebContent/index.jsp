<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Zdravo svete</title>
</head>
<body>
<% 
Date d=new Date(); 
Calendar c = Calendar.getInstance(); 
c.setTime(d); 
c.add(Calendar.DATE, 1);
Date dt = c.getTime();
%>
Zdravo svete! Sad je: <%= d.toString()+". " %>
Sutra ce biti <%= dt %>.
</body>
</html>