<%@ page language="java" contentType="application/json" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

{
	"decks": [<c:forEach items="${deckList}" var="deck" varStatus="loop">${deck.id}<c:if test="${!loop.last}">,</c:if></c:forEach>]	
}