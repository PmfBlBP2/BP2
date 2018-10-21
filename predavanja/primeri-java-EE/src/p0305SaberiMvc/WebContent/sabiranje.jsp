<%-- Order Page  --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" import="java.util.*,operacije.*"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Operacije</title>
</head>
<body>
	<h2>Operacije</h2>
	<hr />
	<br />

	<p>
		<strong>Odredi operande:</strong>
	</p>
	<%
		List<Double> operandi = (List<Double>) session.getAttribute("operandi");
		Double rezultat = (Double) session.getAttribute("rezultat");
	%>
	<form name="AddForm" action="operacije" method="POST">
		<input type="hidden" name="todo" value="saberi"> Operand 1:
		<input name="op1"
			value="
			<%
	           if (operandi != null && operandi.size() == 2)
		    		out.println("" + operandi.get(0));
			   else
			 		out.println(" 0" );
			%>">
		Operand 2: <input name="op2"
			value="
			<%
	           if (operandi != null && operandi.size() == 2)
		    		out.println("" + operandi.get(1));
			   else
			 		out.println(" 0" );
			%>">
		<input type="submit" value="+">
	</form>
	<br />
	<hr />
	<br />
	<% 
      if( rezultat != null )
      {
    %>
	<p>
		<strong>Suma je:</strong>
        <input name="rez"
			value="
			<% out.println("" + rezultat);
			%>">	</p>
	<%
		} // 
	%>
	<br />

	<form name="brisiForm" action="operacije" method="POST">
		<input type="hidden" name="todo" value="brisi"> 
		<input
			type="submit" value="Brisi">
	</form>
</body>
</html>