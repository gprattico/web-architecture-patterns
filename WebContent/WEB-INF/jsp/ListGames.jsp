<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

{
	"games": [
		<c:forEach items="${gameList}" var="gameList" varStatus="loop">
			{"id": ${gameList.id}, "version": ${gameList.version},"players": [${gameList.challengerID}, ${gameList.challengeeID}]}<c:if test="${!loop.last}">,</c:if>
		</c:forEach>
	]
}