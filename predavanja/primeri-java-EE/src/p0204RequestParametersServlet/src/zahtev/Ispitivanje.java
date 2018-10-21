package zahtev;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Ispitivanje
 */
@WebServlet("/IspitivanjeParametara")
public class Ispitivanje extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Ispitivanje() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Enumeration<String> paramsImena = request.getParameterNames();
		
		PrintWriter odgovor = response.getWriter();  
		odgovor.println( 
				  "<html>" 
				+ "<head>"
				+ "  <title> Zahtevi </title>"
				+ "</head>"
				+ "<body>" );
		odgovor.println( 
			  "<table>" );
    while( paramsImena.hasMoreElements() )
    {
    	  String imeParametra = paramsImena.nextElement();
    	  String vrednostParametra = request.getParameter(imeParametra);
    	  odgovor.println(
    	  		"<tr>" +
    	      "    <td>" + imeParametra + "</td>" +
    	      "    <td>" + vrednostParametra + "</td>" +
    	      "</tr>"
    	    	  		);
    }
		odgovor.println(
				  "</table>"
			  + "</body>"
				+ "</html>"
									);

			
	}

}
