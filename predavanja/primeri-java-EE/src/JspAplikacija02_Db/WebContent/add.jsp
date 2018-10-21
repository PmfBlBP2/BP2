<%@page import="java.sql.*"%>

<%@page import="java.io.*"%>

<%@page import="javax.sql.*"%>

<%@page import="java.sql.Connection"%>

<%
	String username=request.getParameter("username");
 
String password=request.getParameter("password");
 
String firstname=request.getParameter("firstname");
 
String lastname=request.getParameter("lastname");
 
String address=request.getParameter("address");
 
String pincode=request.getParameter("pincode");
 
String phonenumber=request.getParameter("phonenumber");
 
Class.forName("com.mysql.jdbc.Driver");
 
Connection con=DriverManager.getConnection
("jdbc:mysql://localhost:3306/ljudi","root","mysqlroot");
 
Statement st=con.createStatement();
 
String sql="insert into info (username,password,firstname,lastname,address,pincode,phonenumber) values('"+username+"','"+password+"','"+firstname+"','"+lastname+"', '"+address+"','"+pincode+"','"+phonenumber+"')";
 
int flag=st.executeUpdate(sql);
 
if(flag==1)
 
{
 
out.println("Added!");
 
}
 
else
 
{
 
out.println("Failed");
 
}
 
response.sendRedirect("show.html");
%>
