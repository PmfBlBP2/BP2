package p01simple.p01Date;
import java.net.*;
import java.io.*;

/** 
 * 
 * @author vlado
 * 
 * Ovde ide neki opis klase koji govori da klasa <code> DateClient </code>
 *koristi klasu  @see {@link ServerSocket} i klasu @see {@link DateServer}
 *
 *
 *
 */
public class DateClient
{
	
  /** 
   * 
   * @param args - niz parametara tipa @see {@link String} kojima se 
   * prenost vrednost iz komandne linije
   */
    public static void main(String[] args)
	{
		try
		{
			/* make connection to server socket */
			Socket sock = new Socket("127.0.0.1", 6013);
			InputStream in = sock.getInputStream();
			BufferedReader bin = new BufferedReader(new InputStreamReader(in));
			/* read the date from the socket */
			String line;
			while ((line = bin.readLine()) != null)
			{
			  String s = "Line from server:" + line;
			  System.out.println(s);
			}
			/* close the socket connection */
			sock.close();
		}
		catch (IOException ioe)
		{
			System.err.println(ioe);
		}
	}
}