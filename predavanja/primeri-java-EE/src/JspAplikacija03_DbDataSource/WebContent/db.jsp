<%@ page language="java" contentType="text/html; charset=ISO-8859-1"

pageEncoding="ISO-8859-1"%>

<%@ page import="rs.ac.bg.matf.oop.p.*" %>

<%

DbConnector dbConn = DbConnector.getInstance();

String firstLastName = dbConn.getFirstLastName();

%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>My DB</title>

</head>

<body>

Hello <%=firstLastName%>!

</body>

</html>