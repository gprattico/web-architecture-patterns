<%@ page language="java" contentType="application/json" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

{	
		"cards": [			
			<c:forEach items="${cardList}" var="card" varStatus="loop">
				<c:set var = "DetailedName" value = "${fn:split(card.name, ' ')[0]}"/>
				<c:if test="${not empty fn:split(card.name, ' ')[1]}">
					<c:set var = "basicName" value = "${fn:split(card.name, ' ')[1]}"/>
				</c:if>
	
				{"id": "${card.id}","t": "${card.type}","n": "${fn:split(card.name, ' ')[0]}" <c:if test="${not empty fn:split(card.name, ' ')[1]}">,"${fn:split(card.name, ' ')[1]}"</c:if>}<c:if test="${!loop.last}">,</c:if>
			</c:forEach>
		]
}