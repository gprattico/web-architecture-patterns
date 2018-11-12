<%@ page language="java" contentType="application/json" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

{
	"board": {
		"id": ${game.id},
		"players": [${game.challengerID}, ${game.challengeeID}],
		"decks": [${challengerDeck.id}, ${challengeeDeck.id}],
		"play": {
			"${game.challengerID}": {
				"status": "playing",
				"handsize": ${challengerHandList},
				"decksize": ${deckSize1},
				"discardsize": ${discardSize1},
				"bench": [<c:forEach items="${benchedListChallenger}" var="card" varStatus="loop">${card.id}<c:if test="${!loop.last}">,</c:if></c:forEach>]
			},
			"${game.challengeeID}": {
				"status": "playing",
				"handsize": ${challengerHandList},
				"decksize": ${deckSize2},
				"discardsize": ${discardSize2},
				"bench": [<c:forEach items="${benchedListChallengee}" var="card" varStatus="loop">${card.id}<c:if test="${!loop.last}">,</c:if></c:forEach>]
			}
		}
	}
}