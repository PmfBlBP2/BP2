/**
 * Copyright (c) 2014 Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://java.net/projects/javaeetutorial/pages/BerkeleyLicense
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This is a simple example of an HTTP Servlet. It responds to the GET method of
 * the HTTP protocol.
 */
@WebServlet("/greeting")
public class GreetingServlet extends HttpServlet
{
  
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException
  {
    String username = request.getParameter("username");
    if (username != null && username.length() > 0)
    {
      RequestDispatcher dispatcher = getServletContext()
          .getRequestDispatcher("/response");
      if (dispatcher != null)
      {
        dispatcher.include(request, response);
      }
    }
  }
  
  @Override
  public String getServletInfo()
  {
    return "The Hello servlet says hello.";
    
  }
}
