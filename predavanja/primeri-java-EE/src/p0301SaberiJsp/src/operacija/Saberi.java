package operacija;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Saberi
 */
@WebServlet("/saberi")
public class Saberi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Saberi() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String arg1 = request.getParameter("operand1");
	    String arg2 = request.getParameter("operand2");

	    double o1 = Double.parseDouble(arg1);
	    double o2 = Double.parseDouble(arg2);
		    
	    PrintWriter piseOdgovor = response.getWriter();
	    piseOdgovor.println(
				  "<html>" + 
				  "   <head>" +
				  "      <title> Sabiranje </title>" +
				  "   </head>" +
				  "   <body>" +
				  "      <h2>" +
				  "         Rezultat je " + (o1+o2) +
				  "      </h2>" +
				  "   </body>" +
				  "<html>" 
				);

	    // Now forward to the JSP.
	    //request.getRequestDispatcher("Saberi.jsp").forward(request, response);
	}
}
