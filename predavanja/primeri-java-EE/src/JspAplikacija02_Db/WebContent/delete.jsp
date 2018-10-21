
<%@page import="java.sql.*"%>
<%@page import="java.io.*"%>
<%@page import="javax.sql.*"%>
<%@page import="java.sql.Connection"%>
<%
	String username = request.getParameter("username");
	Class.forName("com.mysql.jdbc.Driver");
	Connection con = DriverManager.getConnection(
			"jdbc:mysql://localhost:3306/ljudi", "root", "mysqlroot");
	Statement st = con.createStatement();
	try
	{
		st.executeUpdate("DELETE FROM info WHERE username = '" + username + "'");
		boolean flag = true;
		if (flag == true)
		{
			out.println("Deleted");
		} else
		{
			out.println("Not possible : error!");
		}
	}
	catch (Exception e)
	{
		out.println("Exception");
	}
%>
