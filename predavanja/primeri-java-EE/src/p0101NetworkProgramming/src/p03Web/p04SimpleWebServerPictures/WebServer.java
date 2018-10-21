package p03Web.p04SimpleWebServerPictures;

import java.io.*;
import java.net.*;
import java.util.*;

class WebServer
{
  public static void main(String args[]) throws Exception
  {
    String linijaZahteva;
    String imeDatoteke;
    
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
    
    // set up the read and write end of the communication socket
    BufferedReader citacOdKlijenta = new BufferedReader(
        new InputStreamReader(soketZaVezu.getInputStream()));
    DataOutputStream tokPremaKlijentu = new DataOutputStream(
        soketZaVezu.getOutputStream());
        
    // retrieve first line of request and set up for parsing
    linijaZahteva = citacOdKlijenta.readLine();
    System.out.println("Request: " + linijaZahteva);
    StringTokenizer tokenizatorLinijeZahteva = new StringTokenizer(
        linijaZahteva);
        
    // check for GET request
    if (tokenizatorLinijeZahteva.nextToken().equals("GET"))
    {
      imeDatoteke = tokenizatorLinijeZahteva.nextToken();
      
      // remove leading slash from line if exists
      if (imeDatoteke.startsWith("/") == true)
        imeDatoteke = imeDatoteke.substring(1);
        
      // access the requested file
      File datoteka = new File(imeDatoteke);
      
      // convert file to a byte array
      int brojBajtovaDatoteke = (int) datoteka.length();
      FileInputStream tokZaCitanjeDatoteke = new FileInputStream(imeDatoteke);
      byte[] bajtoviDatoteke = new byte[brojBajtovaDatoteke];
      tokZaCitanjeDatoteke.read(bajtoviDatoteke);
      
      // Send reply
      tokPremaKlijentu.writeBytes("HTTP/1.0 200 Document Follows\r\n");
      if (imeDatoteke.endsWith(".jpg"))
        tokPremaKlijentu.writeBytes("Content-Type: image/jpeg\r\n");
      if (imeDatoteke.endsWith(".gif"))
        tokPremaKlijentu.writeBytes("Content-Type: image/gif\r\n");
      tokPremaKlijentu
          .writeBytes("Content-Length: " + brojBajtovaDatoteke + "\r\n");
      tokPremaKlijentu.writeBytes("\r\n");
      tokPremaKlijentu.write(bajtoviDatoteke, 0, brojBajtovaDatoteke);
      
      // read and print out the rest of the request
      linijaZahteva = citacOdKlijenta.readLine();
      while (linijaZahteva.length() >= 5)
      {
        System.out.println("Request: " + linijaZahteva);
        linijaZahteva = citacOdKlijenta.readLine();
      }
      System.out.println("Request: " + linijaZahteva);
      
      tokZaCitanjeDatoteke.close();
      soketZaVezu.close();
      soketZaSlusanje.close();
    } 
    else
    {
      System.out.println("Bad Request Message");
    }
  }
}
