<%@ page language="java" contentType="application/json" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>

{
	"hand": [<c:forEach items="${handList}" var="card" varStatus="loop">${card.id}<c:if test="${!loop.last}">,</c:if></c:forEach>]
}