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
<!-- Bootstrap -->
<link href="<c:url value='/resources/css/bootstrap.min.css'/>" rel="stylesheet" media="screen">
<link href="<c:url value='/resources/css/font-awesome.css/'/>" rel="stylesheet" media="screen">
<link href="<c:url value='/resources/css/main.css'/>" rel="stylesheet" media="screen">
<style>
    .error { font-weight: bold; color: #900; }
</style>
</head>
<body>
    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <a id="homePage" class="navbar-brand" href="computerdatabase"> Application - Computer Database </a>
        </div>
    </header>
    

    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
                    <h1>Add Computer</h1>
                    
                    
                    <form:form action="addComputer"
                    	method="POST" modelAttribute="computerForm">
                    <input name="action" type="hidden" value="add" />
                    	<fieldset>
                    	
                    	<spring:bind path="name">
                            <div class="form-group">
                                <label for="computerName">Computer name</label>
                                <form:input path="name" type="text" class="form-control" id="computerName" placeholder="Computer name"/>
                                <form:errors path="name" cssClass="error"/>
                                <span id="nameError" class="error" style="display:none">A valid name is required !</span>        
                            </div>
                        </spring:bind>
                        
                        <spring:bind path="introduced">
                            <div class="form-group">
                                <label for="introduced">Introduced date</label>
                                <form:input path="introduced" type="date" class="form-control" id="introduced" placeholder="Introduced date" name="introduced"/>
                            </div>
                        </spring:bind>
                        
                        <spring:bind path="discontinued">
                            <div class="form-group">
                                <label for="discontinued">Discontinued date</label>
                                <form:input path="discontinued" type="date" class="form-control" id="discontinued" placeholder="Discontinued date" name="discontinued"/>
                                <span id="dateError" class="error" style="display:none">Discontinued date can't be before introduced date !</span>
                            </div>
						</spring:bind>
                        
                        <spring:bind path="manufacturerId">
                            <div class="form-group">
								<label for="companyId">Company</label>
								<form:select path="manufacturerId" name="companyId" class="form-control" id="companyId">
									<form:option value="0"></form:option>
									<c:forEach items="${companies}" var="company">										
										<form:option value="${company.id}">${company.name}</form:option>
									</c:forEach>
								</form:select>
							</div>          
						</spring:bind>
                        
                        </fieldset>
                        <div class="actions pull-right">
	                        <input id="validAdd" type="submit" value="Add" class="btn btn-primary">
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
	<!-- <script src="<c:url value='/resources/js/addComputer.js'/>"></script> -->
</body>
</html>