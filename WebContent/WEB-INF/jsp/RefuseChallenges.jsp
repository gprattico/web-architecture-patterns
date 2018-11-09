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
<h3> Choose a Challenge to refuse</h3>
	

<form method="post">
	<label> Select one </label>
	<select name="challenges">
		<c:forEach items="${challenges}" var="choice" varStatus="loop">
			<option value="${choice.key}">${choice.value}</option>
		</c:forEach>
	</select>
	<br><br><br>
	<button type="submit">Refuse</button>
</form>




</center>



</body>
</html>