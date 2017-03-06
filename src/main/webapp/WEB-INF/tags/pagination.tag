<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="hashtag" tagdir="/WEB-INF/tags" %>
<%@ attribute name="currentPage" required="true" type="java.lang.Integer" description="Num of current page"%>
<%@ attribute name="size" required="false" type="java.lang.Integer" description="Number of elements per page"%>
<%@ attribute name="numbers" required="true" type="java.lang.Integer" description="Number of computers"%>
<%@ attribute name="search" required="true" type="java.lang.String" description="String to make search"%>
<div class="container text-center">
	<ul class="pagination">
		<c:choose>
			<c:when test="${currentPage > 1}">
				<li>
					<hashtag:link body="<span aria-hidden='true'>&laquo;</span>" limit="${size}" numOfPage="${currentPage - 1}" search="${search}" target="computerdatabase"/>
				</li>
			</c:when>
			<c:otherwise>
				<li class="disabled">
					<hashtag:link body="<span aria-hidden='true'>&laquo;</span>" limit="${size}" numOfPage="${currentPage - 1}" search="${search}" classes="disabled" target="computerdatabase"/>
				</li>
			</c:otherwise>
		</c:choose>
		<c:forEach var="i" begin="${(currentPage - 2 > 0) ? (currentPage - 2) : 1}" end="${(currentPage + 2 < (numbers / size)) ? (currentPage + 2) : (numbers / size)}">
			<c:choose>
				<c:when test="${currentPage == i + 1}">
					<li class="active">
						<hashtag:link body="${i + 1}" limit="${size}" numOfPage="${i + 1}" search="${search}" classes="disabled" target="computerdatabase"/>
					</li>
				</c:when>
				<c:otherwise>
					<li>
						<hashtag:link body="${i + 1}" limit="${size}" numOfPage="${i + 1}" search="${search}" target="computerdatabase"/>
					</li>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:choose> 
			<c:when test="${(currentPage * size) < numbers}">
				<li>
					<hashtag:link body="<span aria-hidden='true'>&raquo;</span>" limit="${size}" numOfPage="${currentPage + 1}" search="${search}" target="computerdatabase"/>
				</li>
			</c:when>
			<c:otherwise>
				<li class="disabled">
					<hashtag:link body="<span aria-hidden='true'>&raquo;</span>" limit="${size}" numOfPage="${currentPage + 1}" search="${search}" classes="disabled" target="computerdatabase"/>
				</li>
			</c:otherwise>
		</c:choose>
	</ul>
	
	<div class="btn-group btn-group-sm pull-right" role="group" >
		<c:choose> 
			<c:when test="${size == 10}">
				<hashtag:link body="10" limit="10" numOfPage="${currentPage}" search="${search}" classes="btn btn-default disabled" target="computerdatabase"/>
			</c:when>
			<c:otherwise>
				<hashtag:link body="10" limit="10" numOfPage="${currentPage}" search="${search}" classes="btn btn-default" target="computerdatabase"/>
			</c:otherwise>
		</c:choose>
		<c:choose> 
			<c:when test="${size == 50}">
				<hashtag:link body="50" limit="50" numOfPage="${currentPage}" search="${search}" classes="btn btn-default disabled" target="computerdatabase"/>
			</c:when>
			<c:otherwise>
				<hashtag:link body="50" limit="50" numOfPage="${currentPage}" search="${search}" classes="btn btn-default" target="computerdatabase"/>
			</c:otherwise>
		</c:choose>
		<c:choose> 
			<c:when test="${size == 100}">
				<hashtag:link body="100" limit="100" numOfPage="${currentPage}" search="${search}" classes="btn btn-default disabled" target="computerdatabase"/>
			</c:when>
			<c:otherwise>
				<hashtag:link body="100" limit="100" numOfPage="${currentPage}" search="${search}" classes="btn btn-default" target="computerdatabase"/>
			</c:otherwise>
		</c:choose>
	</div>
</div>