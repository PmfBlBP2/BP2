
import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloServlet
 */
@WebServlet("/Hello")
public class HelloServlet extends HttpServlet
{
  private static final long serialVersionUID = 1L;
  
  @javax.inject.Inject
  private HelloPOJO pojo;
  
  /**
   * @see HttpServlet#HttpServlet()
   */
  public HelloServlet()
  {
    super();
    // TODO Auto-generated constructor stub
  }
  
  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException
  {
    response.setContentType("text/html");
    final Writer pisac = response.getWriter();
    pisac.append("<html>");
    pisac.append("<body>");
    pisac.append("<h1>");
    pisac.append("Hello Servlet!");
    pisac.append("</h1>");
    pisac.append("<h1>");
    pisac.append("Hello " + pojo.from() + " !");
    pisac.append("</h1>");
    pisac.append("</body>");
    pisac.append("</html>");
  }
  
  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doPost(HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException
  {
    // TODO Auto-generated method stub
  }
  
}
