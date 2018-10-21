package model;

public class CartItem
{
	private int bookID;
	private int qtyOrdered;
	
	public CartItem(int bookID, int qtyOrdered)
	{
		this.bookID = bookID;
		this.qtyOrdered = qtyOrdered;
	}
	
	public int getBookID()
	{
		return bookID;
	}
	
	public void setBookID(int bookID)
	{
		this.bookID = bookID;
	}
	
	public int getQtyOrdered()
	{
		return qtyOrdered;
	}
	
	public void setQtyOrdered(int qtyOrdered)
	{
		this.qtyOrdered = qtyOrdered;
	}
	
	// Returns the author based on this bookID
	public String getAuthor()
	{
		try
		{
			BookDb db = new BookDb();
			return db.getAuthorById(bookID);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "N/A";
		}
	}
	
	// Returns the author based on this bookID
	public String getTitle()
	{
		try
		{
			BookDb db = new BookDb();
			return db.getTitleById(bookID);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "N/A";
		}
	}
	
	// Return the price based on this bookID
	public float getPrice()
	{
		try
		{
			BookDb db = new BookDb();
			return db.getPriceById(bookID);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return -1f;
		}
	}
}
