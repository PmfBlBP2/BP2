package p03Web.p03VerySimpleWebServerHtml;

import java.io.*;
import java.net.*;
import java.util.Date;

class WebServer
{
  public static void main(String args[]) throws Exception
  {
    // check if a port number is given as the first command line argument
    // if not argument is given, use port number 6022
    int brojPorta = 6022;
    if (args.length > 0)
    {
      try
      {
        brojPorta = Integer.parseInt(args[0]);
      }
      catch (ArrayIndexOutOfBoundsException e)
      {
        System.out.println("Need port number as argument");
        System.exit(-1);
      }
      catch (NumberFormatException e)
      {
        System.out.println("Please give port number as integer.");
        System.exit(-1);
      }
    }
    
    // set up connection socket
    ServerSocket soketZaSlusanje = new ServerSocket(brojPorta);
    
    // listen (i.e. wait) for connection request
    System.out.println("Web server waiting for request on port " + brojPorta);
    Socket soketZaVezu = soketZaSlusanje.accept();
    
    // set up the write end of the communication socket
    DataOutputStream tokPremaKlijentu = new DataOutputStream(
        soketZaVezu.getOutputStream());
    Date datum = new Date();
    String htmlStrana = "<html><body>" + "<h2>Zdravo studenti!</h2>" + "<br/> "
        + "<h3>Datum i vreme =" + datum + "</h3>" + "</body></html>";
    // Send reply
    tokPremaKlijentu.writeBytes("HTTP/1.0 200 Document Follows\r\n");
    tokPremaKlijentu.writeBytes("Content-Type: text/html\r\n");
    tokPremaKlijentu
        .writeBytes("Content-Length: " + htmlStrana.length() + "\r\n");
    tokPremaKlijentu.writeBytes("\r\n");
    tokPremaKlijentu.writeBytes(htmlStrana);
    
    soketZaVezu.close();
    soketZaSlusanje.close();
  }
}
