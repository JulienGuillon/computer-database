<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link href="<c:url value='/resources/css/bootstrap.min.css'/>" rel="stylesheet" media="screen">
<link href="<c:url value='/resources/css/font-awesome.css/'/>" rel="stylesheet" media="screen">
<link href="<c:url value='/resources/css/main.css'/>" rel="stylesheet" media="screen">
</head>
<body>
    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <a class="navbar-brand" href="computerdatabase"> Application - Computer Database </a>
        </div>
    </header>
    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
                    <div class="label label-default pull-right">
                        id: <c:out value="${computer.id}"/>
                    </div>
                    <h1>Edit Computer</h1>

                    <form:form action="editComputer"
                    	method="POST" modelAttribute="computerEdit">
                        <input name="action" type="hidden" value="edit" id="${computer.id}"/>
                        <form:input path="id" name="id" type="hidden" value="${computer.id}" id="${computer.id}"/>
                        
                        <fieldset>
                            <div class="form-group">
                                <label for="computerName">Computer name</label>
                                <form:input path="name" type="text" class="form-control" id="computerName" placeholder="${computer.name}" name="computerName" value="${computer.name}"/>
                            </div>
                            <div class="form-group">
                                <label for="introduced">Introduced date</label>
                                <form:input path="introduced" type="date" class="form-control" id="introduced" placeholder="${computer.introduced}" name="introduced"/>
                            </div>
                            <div class="form-group">
                                <label for="discontinued">Discontinued date</label>
                                <form:input path="discontinued" type="date" class="form-control" id="discontinued" placeholder="${computer.discontinued}" name="discontinued"/>
                            </div>
                            <div class="form-group">
                                <label for="companyId">Company</label>
                                <form:select path="manufacturerId" name="companyId" class="form-control" id="companyId" >
                                    <form:option value="${computer.manufacturerId}"><c:out value="${computer.manufacturerName}"/></form:option>
									<c:forEach items="${companies}" var="company">										
										<form:option value="${company.id}">${company.name}</form:option>
									</c:forEach>
                                </form:select>
                            </div>            
                        </fieldset>
                        <div class="actions pull-right">
                            <input type="submit" value="Edit" class="btn btn-primary">
                            or
                            <a href="computerdatabase" class="btn btn-default">Cancel</a>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </section>
<script src="<c:url value='/resources/js/jquery.min.js'/>"></script>
<script src="<c:url value='/resources/js/bootstrap.min.js'/>"></script>
<script src="<c:url value='/resources/js/addComputer.js'/>"></script>
</body>
</html>