<%-- Order Page  --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" import="java.util.*,checkout.*"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>E-BookShop Order</title>
</head>
<body>
	<h2>Yet Another E-Bookshop</h2>
	<hr />
	<br />

	<p>
		<strong>Choose a book and enter the quantity:</strong>
	</p>
	<form name="AddForm" action="shopping" method="POST">
		<input type="hidden" name="todo" value="add"> Select Book: <select
			name=bookID>
			<%
				// Scriptlet 1: Populate the books into the "select" control.
				for (int i = 0; i < BookDb.size(); ++i)
				{
					out.println("<option value='" + i + "'>");
					out.println(BookDb.getTitle(i) + " | " + BookDb.getAuthor(i) + " | "
							+ BookDb.getPrice(i));
					out.println("</option>");
				}
			%>
		</select> Enter Quantity: <input type="text" name="qty" size="10" value="1">
		<input type="submit" value="Add to Shopping Cart">
	</form>
	<br />
	<hr />
	<br />

	<%
		// Scriptlet 2: Check whether the shopping cart is empty.
		List<CartItem> theCart = (List<CartItem>) session.getAttribute("cart");
		if (theCart != null && theCart.size() > 0)
		{
	%>
	<p>
		<strong>Your shopping cart contains:</strong>
	</p>
	<table border="1" cellspacing="0" cellpadding="5">
		<tr>
			<th>Title</th>
			<th>Author</th>
			<th>Price</th>
			<th>Quantity</th>
			<th>&nbsp;</th>
		</tr>
		<%
			// Scriptlet 3: display the books in the shopping cart.
				for (int i = 0; i < theCart.size(); ++i)
				{
					CartItem aCartItem = theCart.get(i);
		%>
		<tr>
			<form name="removeForm" action="shopping" method="POST">
				<input type="hidden" name="todo" value="remove"> <input
					type="hidden" name="cartIndex" value="<%=i%>">
				<td><%=aCartItem.getTitle()%></td>
				<td><%=aCartItem.getAuthor()%></td>
				<td align="right">$<%=aCartItem.getPrice()%></td>
				<td align="right"><%=aCartItem.getQtyOrdered()%></td>
				<td><input type="submit" value="Remove from Shopping Cart"></td>
			</form>
		</tr>
		<%
			} // for loop
		%>
	</table>
	<br />

	<form name="checkoutForm" action="shopping" method="POST">
		<input type="hidden" name="todo" value="checkout"> <input
			type="submit" value="Checkout">
	</form>
	<%
		} // if
	%>
</body>
</html>