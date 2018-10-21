package operacije;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/operacije")
// Define URL pattern (for servlet 3.0 only)
public class OperacijeServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	@Override
	public void init(ServletConfig conf) throws ServletException
	{
		super.init(conf);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		doPost(request, response); // Same as doPost()
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		// Retrieve the current session, or create a new session if no session
		// exists.
		HttpSession session = request.getSession(true);
		
		// Retrieve the shopping cart of the current session.
		List<Double> operandi = (ArrayList<Double>) session.getAttribute("operandi");
		Double rezultat = (Double) session.getAttribute("rezultat");
		
		// For dispatching the next Page
		String nextPage = "";
		String todo = request.getParameter("todo");		
		if (todo == null)
		{
			// First access - redirect to order.jsp
			nextPage = "/sabiranje.jsp";
		}	else if (todo.equals("saberi"))
		{
			if( operandi == null )
			{
				operandi = new ArrayList<>();
				operandi.add(-1.0);
				operandi.add(-2.0);
			}
			operandi.set( 0, Double.parseDouble(request.getParameter("op1")));
			operandi.set( 1, Double.parseDouble(request.getParameter("op2")));
			rezultat = operandi.get(0) + operandi.get(1);
			session.setAttribute("operandi", operandi);
			session.setAttribute("rezultat", rezultat);
			nextPage = "/sabiranje.jsp";
		} else if (todo.equals("brisi"))
		{
			session.setAttribute("operandi", null);
			session.setAttribute("rezultat", null);
			nextPage = "/sabiranje.jsp";
		}
		ServletContext servletContext = getServletContext();
		RequestDispatcher requestDispatcher = servletContext
				.getRequestDispatcher(nextPage);
		requestDispatcher.forward(request, response);
	}
}
