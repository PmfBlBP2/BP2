package p01simple.p01Date;
import java.net.*;
import java.io.*;

/** 
 * 
 * @author vlado
 * 
 * Ovde ide neki opis klase koji govori da klasa <code> DateServer </code>
 *koristi klasu  @see {@link ServerSocket} 
 *
 *
 *
 */

public class DateServer
{
	public static void main(String[] args)
	{
		try
		{
			ServerSocket sock = new ServerSocket(6013);
			/* now listen for connections */
			while (true)
			{
				Socket client = sock.accept();
				PrintWriter pout = new PrintWriter(client.getOutputStream(), true);
				/* write the Date to the socket */
				pout.println(new java.util.Date().toString() + " RS2");
				/* close the socket and resume */
				/* listening for connections */
				client.close();
			}
		}
		catch (IOException ioe)
		{
			System.err.println(ioe);
		}
	}
}