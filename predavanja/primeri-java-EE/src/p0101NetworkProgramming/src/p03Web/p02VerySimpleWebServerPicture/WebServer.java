package p03Web.p02VerySimpleWebServerPicture;

import java.io.*;
import java.net.*;

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
        
    // access the requested file
    String imeDatoteke = "zamak.jpg";
    File datoteka = new File(imeDatoteke);
    
    // convert file to a byte array
    int brojBajtovaDatoteke = (int) datoteka.length();
    FileInputStream tokZaCitanje = new FileInputStream(imeDatoteke);
    byte[] bajtoviDatoteke = new byte[brojBajtovaDatoteke];
    tokZaCitanje.read(bajtoviDatoteke);
    
    // Send reply
    tokPremaKlijentu.writeBytes("HTTP/1.1 200 Document Follows\r\n");
    if (imeDatoteke.endsWith(".jpg"))
      tokPremaKlijentu.writeBytes("Content-Type: image/jpeg\r\n");
    if (imeDatoteke.endsWith(".gif"))
      tokPremaKlijentu.writeBytes("Content-Type: image/gif\r\n");
    tokPremaKlijentu.writeBytes("Content-Length: " + brojBajtovaDatoteke + "\r\n");
    tokPremaKlijentu.writeBytes("\r\n");
    tokPremaKlijentu.write(bajtoviDatoteke, 0, brojBajtovaDatoteke);
    
    tokZaCitanje.close();
    soketZaVezu.close();
    soketZaSlusanje.close();
  }
}
