<%@ page language="java" contentType="application/json" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
{
	"deck": {
			"id": ${deckID},
			"cards": [
				<c:forEach items="${cardList}" var="card" varStatus="loop">
				{"t": "${card.type}","n": "${card.name}"}<c:if test="${!loop.last}">,</c:if>
				</c:forEach>
			]
	}
}