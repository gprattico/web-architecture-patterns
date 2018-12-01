<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

{
	"challenges": [
		<c:forEach items="${challenge}" var="challenge" varStatus="loop">
			{"id": ${challenge.id}, "version": ${challenge.version}, "challenger": ${challenge.challenger}, "challengee": ${challenge.challengee}, "status": ${challenge.status}, "deck": ${challenge.deckOfChallenger}}<c:if test="${!loop.last}">,</c:if>
		</c:forEach>
	]
}