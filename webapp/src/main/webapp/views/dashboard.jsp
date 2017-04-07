<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="page" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><spring:message code="cdb.dashboard.title"/></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<!-- Bootstrap -->
<link href="<c:url value='/resources/css/bootstrap.min.css'/>" rel="stylesheet" media="screen">
<link href="<c:url value='/resources/css/font-awesome.css/'/>" rel="stylesheet" media="screen">
<link href="<c:url value='/resources/css/main.css'/>" rel="stylesheet" media="screen">
</head>

<body>
    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <a id="homePage" class="navbar-brand" href="computerdatabase"> <spring:message code="cdb.dashboard.home"/> </a>
        </div>
        lang : <a href="?locale=en">English</a> | <a
				href="?locale=fr">French</a>
    </header>

    <section id="main">
        <div class="container">
            <h1 id="homeTitle">
            	${numberOfComputers} <spring:message code="cdb.dashboard.count"/>            	
            </h1>
            <div id="actions" class="form-horizontal">
                <div class="pull-left">
                    <form id="searchForm" action="#" method="GET" class="form-inline">

                        <input type="search" id="searchbox" name="filter" class="form-control" placeholder="<spring:message code="cdb.dashboard.search"/>"/>
                        <input type="submit" id="searchsubmit" value="<spring:message code="cdb.dashboard.search"/>" class="btn btn-primary"/>
                    </form>
                </div>
                <div class="pull-right">
                    <a class="btn btn-success" id="addComputer" href="addComputer"><spring:message code="cdb.dashboard.add"/></a> 
                    <a class="btn btn-default" id="editComputer" onclick="$.fn.toggleEditMode();"><spring:message code="cdb.dashboard.edit"/></a>
                </div>
            </div>
        </div>

        <form id="deleteForm" action="computerdatabase" method="POST">
            <input type="hidden" name="selection" value="">
        </form>

        <div class="container" style="margin-top: 10px;">
            <table class="table table-striped table-bordered">
                <thead>
                    <tr>
                        <!-- Variable declarations for passing labels as parameters -->
                        <!-- Table header for Computer Name -->

                        <th class="editMode" style="width: 60px; height: 22px;">
                            <input type="checkbox" id="selectall" /> 
                            <span style="vertical-align: top;">
                                 -  <a href="#" id="deleteSelected" onclick="$.fn.deleteSelected();">
                                        <i class="fa fa-trash-o fa-lg"></i>
                                    </a>
                            </span>
                        </th>
                        <th>
                            <spring:message code="cdb.dashboard.name"/>
                        </th>
                        <th>
                            <spring:message code="cdb.dashboard.introduced"/>
                        </th>
                        <!-- Table header for Discontinued Date -->
                        <th>
                            <spring:message code="cdb.dashboard.discontinued"/>
                        </th>
                        <!-- Table header for Company -->
                        <th>
                            <spring:message code="cdb.dashboard.company"/>
                        </th>

                    </tr>
                </thead>
                
                <tbody id="results">
				<c:forEach items="${computers}" var="computer">
					<page:edit computerId="${computer.id}" computerName="${computer.name}" computerIntroduced="${computer.introduced}"
					computerDiscontinued="${computer.discontinued}" computerManufacturerName="${computer.manufacturerName}">
					</page:edit>
				</c:forEach>

                </tbody>
            </table>
        </div>
    </section>

	<footer class="navbar-fixed-bottom">
		<page:pagination currentPage="${currentPage}" size="${limit}" numbers="${numberOfComputers}" filter="${filter}">
		</page:pagination>
	</footer>
<script src="<c:url value='/resources/js/jquery.min.js'/>"></script>
<script src="<c:url value='/resources/js/bootstrap.min.js'/>"></script>
<script src="<c:url value='/resources/js/dashboard.js'/>"></script>

</body>
</html>