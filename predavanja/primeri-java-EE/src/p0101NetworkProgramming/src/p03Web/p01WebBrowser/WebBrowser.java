package p03Web.p01WebBrowser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class WebBrowser
{
  
  private static void browse(String urlAddesss)
  {
    URL url;
    InputStream tok = null;
    BufferedReader citac;
    String linija;
    
    try
    {
      url = new URL(urlAddesss);
      tok = url.openStream(); // throws an IOException
      citac = new BufferedReader(new InputStreamReader(tok));
      
      while ((linija = citac.readLine()) != null)
      {
        System.out.println(linija);
      }
    }
    catch (MalformedURLException mue)
    {
      mue.printStackTrace();
    }
    catch (IOException ioe)
    {
      ioe.printStackTrace();
    }
    finally
    {
      try
      {
        if (tok != null)
          tok.close();
      }
      catch (IOException ioe)
      {
        // nothing to see here
      }
    }
    
  }
  
  public static void main(String[] args)
  {
    String url = "http://www.matf.bg.ac.rs/~vladaf";
    
    if (args.length > 0)
    {
      url = args[0];
    } else
    {
      System.out.println("Unesite veb adresu:");
      Scanner sc = new Scanner(System.in);
      url = sc.next();
      sc.close();
    }
    System.out.println("\n Sadzaj veb strane:");
    browse(url);
  }
}
