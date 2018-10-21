package rs.ac.bg.matf.oop.p;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;
import java.util.List;

import javax.sql.DataSource;
import javax.naming.*;

@WebServlet("/Proba")
public class MySqlDbcpServlet extends HttpServlet
{
  
  DataSource pool; // Database connection pool
  
  @Override
  public void init() throws ServletException
  {
    try
    {
      // Create a JNDI Initial context to be able to lookup the DataSource
      InitialContext ctx = new InitialContext();
      // Lookup the DataSource, which will be backed by a pool
      // that the application server provides.
      pool = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
      if (pool == null)
        throw new ServletException("Unknown DataSource 'jdbc/TestDB'");
    }
    catch (NamingException ex)
    {
      ex.printStackTrace();
    }
  }
  
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException
  {
    // Set the response message's MIME type
    response.setContentType("text/html;charset=UTF-8");
    // Allocate a output writer to write the response message into the
    // network socket
    PrintWriter out = response.getWriter();
    
    BookDAO dao = new BookDAO(pool);
    List<Book> books = dao.getAllBooks();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head><title>Qurey Servlet</title></head>");
    out.println("<body>");
    int count = 0;
    
    for (Book b : books)
    {
      out.println("<p>" + b.getTitle() + ", " + b.getAuthor() + "</p>");
      ++count;
    }
    out.println("<p>==== " + count + " rows found =====</p>");
    out.println("</body></html>");
  }
  
}