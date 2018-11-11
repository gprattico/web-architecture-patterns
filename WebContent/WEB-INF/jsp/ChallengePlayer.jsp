<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Choose a player to challenge</title>
</head>
<body>
<center>
<h3> Choose a player to Challenge</h3>
	

<form method="post">
	<label> Select one </label>
	<select name="player">
		<c:forEach items="${player}" var="choice">
			<option value="${choice.id}">${choice.username}</option>
		</c:forEach>
	</select>
	<br><br><br>
	<button type="submit">Challenge</button>
</form>




</center>



</body>
</html>