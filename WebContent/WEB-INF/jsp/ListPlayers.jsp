<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

{
	"players": [
		<c:forEach items="${players}" var="user" varStatus="loop">
			{"id": ${user.id}, "user": "${user.username}"}<c:if test="${!loop.last}">,</c:if>
		</c:forEach>
	]
}