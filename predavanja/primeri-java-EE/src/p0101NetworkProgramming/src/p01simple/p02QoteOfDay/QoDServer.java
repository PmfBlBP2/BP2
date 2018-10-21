package p01simple.p02QoteOfDay;

import java.net.*;
import java.io.*;

public class QoDServer
{
	private static String qodMessage()
	{
		int day = new java.util.Date().getDay();
		switch (day % 7)
		{
			case 0:
				return "U radise svega bise \nu stedise jos i vise \n";
			case 1:
				return "Magarca ne zovu na svadbu da se veseli \n "
						+ "vec da radi i da drva nosi\n";
			case 2:
				return "Ko rano rani, celi dan zeva \n";
			case 3:
				return "Vrana vrani oci ne vadi \n";
			case 4:
				return "Bolje vrabac u ruci, \nnego golub na grani\n";
			case 5:
				return "Protivnik je brojno jaci: \n"
						+ "njih su dvojica, a mi smo sami\n";
			case 6:
				return "Boj ne bije svijetlo oruzje \n"
						+ "vec boj bije srce u junaka\n";
			default:
				return "Ko drugome jamu kopa \nSam u nju upada \n";
		}
	}

	public static void main(String[] args)
	{
		try
		{
			int port = 6017;
			ServerSocket sock = new ServerSocket(port);
			System.out.printf("QoD server is started. It is waiting requestes on port %d...", port);
			/* now listen for connections */
			while (true)
			{
				Socket client = sock.accept();
				PrintWriter pout = new PrintWriter(client.getOutputStream(), true);
				/* write the QoD to the socket */
				pout.println(qodMessage());
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