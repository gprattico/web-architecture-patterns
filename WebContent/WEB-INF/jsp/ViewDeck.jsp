<%@ page language="java" contentType="application/json" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
{
		"cards": [
			<c:forEach items="${cardList}" var="card" varStatus="loop">
			{"id": "${card.id}","t": "${card.type}","n": "${card.name}"}<c:if test="${!loop.last}">,</c:if>
			</c:forEach>
		]
	}
}