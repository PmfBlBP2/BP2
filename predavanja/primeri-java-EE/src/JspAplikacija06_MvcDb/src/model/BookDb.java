package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.sql.DataSource;

public class BookDb
{
	
	DataSource pool; // Database connection pool
	
	public BookDb() throws Exception
	{
		try
		{
			// Create a JNDI Initial context to be able to lookup the DataSource
			InitialContext ctx = new InitialContext();
			// Lookup the DataSource, which will be backed by a pool
			// that the application server provides.
			pool = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
			if (pool == null)
				throw new Exception("Unknown DataSource 'jdbc/TestDB'");
		}
		catch (NamingException ex)
		{
			ex.printStackTrace();
		}
	}
	
	public int size()
	{
		try
		{
			Connection conn = pool.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery("SELECT COUNT(*) FROM books");
			int ret = 0;
			while (rset.next())
			{
				ret = rset.getInt(1);
			}
			conn.close();
			return ret;
		}
		catch (SQLException exp)
		{
			return -1;
		}
	}
	
	public int getIdByPosition(int position)
	{
		Connection conn = null;
		try
		{
			conn = pool.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery("SELECT id FROM books");
			int size = size();
			if (position > size)
				return -1;
			int ret = 0;
			for (int i = 1; i <= position; i++)
				rset.next();
			ret = rset.getInt(1);
			return ret;
		}
		catch (SQLException exp)
		{
			return -1;
		}
		finally
		{
			try
			{
				if (conn != null)
					conn.close();
			}
			catch (SQLException exp)
			{
				// Ne treba "gusiti" izuzetke
			}
		}
		
	}
	
	/** Return the title of the given bookID */
	public String getTitleById(int bookID)
	{
		try
		{
			Connection conn = pool.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery("SELECT title FROM books WHERE id = "
					+ bookID);
			String ret = "";
			while (rset.next())
			{
				ret = rset.getString(1);
			}
			conn.close();
			return ret;
		}
		catch (SQLException exp)
		{
			return "N/A";
		}
	}
	
	/** Return the author of the given bookID */
	public String getAuthorById(int bookID)
	{
		try
		{
			Connection conn = pool.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery("SELECT author FROM books WHERE id = "
					+ bookID);
			String ret = "";
			while (rset.next())
			{
				ret = rset.getString(1);
			}
			conn.close();
			return ret;
		}
		catch (SQLException exp)
		{
			return "N/A";
		}
	}
	
	/** Return the price of the given bookID */
	public float getPriceById(int bookID)
	{
		try
		{
			Connection conn = pool.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery("SELECT price FROM books WHERE id = "
					+ bookID);
			float ret = 0f;
			while (rset.next())
			{
				ret = rset.getFloat(1);
			}
			conn.close();
			return ret;
		}
		catch (SQLException exp)
		{
			return -1f;
		}
	}
}
