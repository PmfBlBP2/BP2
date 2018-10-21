<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Prikaz parametara</title>
</head>
<body>
	<table border="1">
		<tr>
			<th>Naziv parametra</th>
			<th>Vrednost parametra</th>
		</tr>
		<% Enumeration<String> paramsImena = request.getParameterNames();
		   while( paramsImena.hasMoreElements() )  
		   { 
		  	  String imeParametra = paramsImena.nextElement();
	    	  String vrednostParametra = request.getParameter(imeParametra);   
		   %>
		     <tr>
		     <td> <%= imeParametra %> </td>
		     <td> <%= vrednostParametra %>  </td>
		     </tr>	   
		   
		 <%}
		   %>
	</table>
</body>
</html>