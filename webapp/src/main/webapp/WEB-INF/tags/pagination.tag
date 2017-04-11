<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="page" tagdir="/WEB-INF/tags" %>
<%@ attribute name="currentPage" required="true" type="java.lang.Integer" description="Num of current page"%>
<%@ attribute name="size" required="true" type="java.lang.Integer" description="Number of elements per page"%>
<%@ attribute name="numbers" required="true" type="java.lang.Integer" description="Number of computers"%>
<%@ attribute name="filter" required="true" type="java.lang.String" description="String to make search"%>
<div class="container text-center">
	<ul class="pagination">
		<c:choose>
			<c:when test="${currentPage > 1}">
				<li>
					<page:link body="<span aria-hidden='true'>&laquo;</span>" limit="${size}" numOfPage="${currentPage - 1}" filter="${filter}" target="computerdatabase"/>
				</li>
			</c:when>
			<c:otherwise>
				<li class="disabled">
					<page:link body="<span aria-hidden='true'>&laquo;</span>" limit="${size}" numOfPage="${currentPage - 1}" filter="${filter}" classes="disabled" target="computerdatabase"/>
				</li>
			</c:otherwise>
		</c:choose>
		<c:forEach var="i" begin="${(currentPage - 3 > 0) ? (currentPage - 3) : 0}" end="${(currentPage + 2 < (numbers / size)) ? (currentPage + 1) : (numbers / size)-1}">
			<c:choose>
				<c:when test="${currentPage == i + 1}">
					<li class="active">
						<page:link body="${i + 1}" limit="${size}" numOfPage="${i + 1}" filter="${filter}" classes="disabled" target="computerdatabase"/>
					</li>
				</c:when>
				<c:otherwise>
					<li>
						<page:link body="${i + 1}" limit="${size}" numOfPage="${i + 1}" filter="${filter}" target="computerdatabase"/>
					</li>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:choose> 
			<c:when test="${((currentPage+1) * size) < numbers}">
				<li>
					<page:link body="<span aria-hidden='true'>&raquo;</span>" limit="${size}" numOfPage="${currentPage + 1}" filter="${filter}" target="computerdatabase"/>
				</li>
			</c:when>
			<c:otherwise>
				<li class="disabled">
					<page:link body="<span aria-hidden='true'>&raquo;</span>" limit="${size}" numOfPage="${currentPage + 1}" filter="${filter}" classes="disabled" target="computerdatabase"/>
				</li>
			</c:otherwise>
		</c:choose>
	</ul>
	
	<div class="btn-group btn-group-sm pull-right" role="group" >
		<c:choose> 
			<c:when test="${size == 10}">
				<page:link body="10" limit="10" numOfPage="${currentPage}" filter="${filter}" classes="btn btn-default disabled" target="computerdatabase"/>
			</c:when>
			<c:otherwise>
				<page:link body="10" limit="10" numOfPage="${currentPage}" filter="${filter}" classes="btn btn-default" target="computerdatabase"/>
			</c:otherwise>
		</c:choose>
		<c:choose> 
			<c:when test="${size == 50}">
				<page:link body="50" limit="50" numOfPage="${currentPage}" filter="${filter}" classes="btn btn-default disabled" target="computerdatabase"/>
			</c:when>
			<c:otherwise>
				<page:link body="50" limit="50" numOfPage="${currentPage}" filter="${filter}" classes="btn btn-default" target="computerdatabase"/>
			</c:otherwise>
		</c:choose>
		<c:choose> 
			<c:when test="${size == 100}">
				<page:link body="100" limit="100" numOfPage="${currentPage}" filter="${filter}" classes="btn btn-default disabled" target="computerdatabase"/>
			</c:when>
			<c:otherwise>
				<page:link body="100" limit="100" numOfPage="${currentPage}" filter="${filter}" classes="btn btn-default" target="computerdatabase"/>
			</c:otherwise>
		</c:choose>
	</div>
</div>