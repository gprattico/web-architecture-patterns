<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

{
	"games": [
		<c:forEach items="${challenge}" var="challenge" varStatus="loop">
			{"id": ${challenge.id}, "players": ["${challenge.challengerID}", "${challenge.challengeeID}"]}<c:if test="${!loop.last}">,</c:if>
		</c:forEach>
	]
}