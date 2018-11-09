<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Upload a deck</title>
</head>
<body>
	<center>
		 <h3>Upload a Deck</h3>
		 
			 <p>Enter a deck of exactly 40 cards.</p>
				 <form method="post">
					<textarea name="deckbox" rows="30" cols="50"></textarea>
					<br>
					<button type="submit">Enter</button>
				</form>
	</center>

</body>
</html>