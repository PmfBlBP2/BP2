package rs.ac.bg.matf.oop.p;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

public class BookDAO
{
  
  DataSource pool;
  
  public BookDAO(DataSource pool)
  {
    this.pool = pool;
  }
  
  public List<Book> getAllBooks()
  {
    Connection conn = null;
    Statement stmt = null;
    List<Book> bookList = new LinkedList<Book>();
    // Get a connection from the pool
    try
    {
      conn = pool.getConnection();  
      // Normal JBDC programming hereafter. Close the Connection to return
      // it to the pool
      stmt = conn.createStatement();
      ResultSet rset = stmt.executeQuery("SELECT title, author FROM books");
      while (rset.next())
      {
        bookList
            .add(new Book(rset.getString("author"), rset.getString("title")));
      }
    }
    catch (SQLException exp)
    {
      exp.printStackTrace();
    }
    return bookList;
  }
  
  private void saveBookList(List<Book> bookList)
  {
    Connection conn = null;
    Statement stmt = null;
    try
    {
      conn = pool.getConnection();  
      // Normal JBDC programming hereafter. Close the Connection to return
      // it to the pool
      stmt = conn.createStatement();
      stmt.execute("DELETE FROM books");
      for( Book b: bookList)
      {
        stmt.execute("INSERT INTO books(title, author) VALUES ('" + b.getAuthor() + "','" + b.getTitle() + "')");
      }
    }
    catch (SQLException exp)
    {
      exp.printStackTrace();
    }
  }
  
}
