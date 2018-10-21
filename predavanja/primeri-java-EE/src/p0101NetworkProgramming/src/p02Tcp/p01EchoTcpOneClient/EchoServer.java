package p02Tcp.p01EchoTcpOneClient;

import java.net.*;
import java.io.*;

public class EchoServer
{
	
	private static String switchCase( String s )
	{
		char[] sa = s.toCharArray();
		String ret = "";
		for( char c: sa)
			if( Character.isLowerCase(c) )
				ret += Character.toUpperCase(c);
			else if( Character.isUpperCase(c) )
				ret += Character.toLowerCase(c);
			else
				ret += c;
		return ret;
	}
	
	public static void main(String[] args) throws IOException
	{
		final int port = 6019;
		ServerSocket serverSocket = null;
		try
		{
			serverSocket = new ServerSocket(port);
		}
		catch (IOException e)
		{
			System.err.printf("Could not listen on port: %d.", port);
			System.exit(1);
		}
		Socket clientSocket = null;
		System.out.println("Waiting for connection.....");
		try
		{
			clientSocket = serverSocket.accept();
		}
		catch (IOException e)
		{
			System.err.println("Accept failed.");
			System.exit(1);
		}
		System.out.println("Connection successful");
		System.out.println("Waiting for input.....");
		PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
		BufferedReader in = new BufferedReader(new InputStreamReader(
				clientSocket.getInputStream()));
		String inputLine;
		while ((inputLine = in.readLine()) != null)
		{
			System.out.println("Server: " + inputLine);
			out.println(switchCase(inputLine));
			if (inputLine.trim().equals("Bye."))
				break;
		}
		out.close();
		in.close();
		clientSocket.close();
		serverSocket.close();
	}
}
