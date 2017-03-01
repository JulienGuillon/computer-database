<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="../css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="../css/font-awesome.css" rel="stylesheet" media="screen">
<link href="../css/main.css" rel="stylesheet" media="screen">
</head>
	<jsp:useBean id="managerBeanCompany" scope="page" class="com.excilys.computerdatabase.manager.ManagerBeanCompany" >
		<jsp:setProperty name="managerBeanCompany" property="*" />
	</jsp:useBean>
	<jsp:useBean id="managerBeanComputer" scope="page" class="com.excilys.computerdatabase.manager.ManagerBeanComputer" >
		<jsp:setProperty name="managerBeanComputer" property="*" />
	</jsp:useBean>
<body>
    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <a class="navbar-brand" href="dashboard.html"> Application - Computer Database </a>
        </div>
    </header>
    
    <c:set var="name" value="etette"/>
    <c:set var="introduced" scope="page" value=""/>
    <c:set var="discontinued" scope="page" value=""/>
    <c:set var="companyId" scope="page" value=""/>
    <c:set var="companyName" scope="page" value=""/>

    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
                    <h1>Add Computer</h1>
                    <form action="test.jsp" method="POST">
                        <fieldset>
                            <div class="form-group">
                                <label for="computerName">Computer name</label>
                                <input type="text" class="form-control" id="computerName" placeholder="Computer name" value="${param.computerName}" name="computerName">
                                <span id="nameError" class="error" style="display:none">A valid name is required !</span>
                                
                            </div>
                            <div class="form-group">
                                <label for="introduced">Introduced date</label>
                                <input type="date" class="form-control" id="introduced" placeholder="Introduced date">
                            </div>
                            <div class="form-group">
                                <label for="discontinued">Discontinued date</label>
                                <input type="date" class="form-control" id="discontinued" placeholder="Discontinued date">
                                <span id="dateError" class="error" style="display:none">Discontinued date can't be before introduced date !</span>
                                
                            </div>
                            <div class="form-group">
                                <label for="companyId">Company</label>
                                <select class="form-control" id="companyId" >
                                <option value="0">-------</option>
                                <c:forEach var="company" items="${managerBeanCompany.companies}">
                                    <option value="${company.id}" ><c:out value="${company.name}"/></option>
                                </c:forEach>
                                </select>
                            </div>                  
                        </fieldset>
                        <p id="test"> TEST </p>
                        <div class="actions pull-right">
                            <button type="submit" class="btn btn-primary" value="Add"></button>
                            or
                            <a href="dashboard.jsp" class="btn btn-default">Cancel</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
    <script src="../js/jquery.min.js"></script>
    <script src="../js/addComputer.js"></script>
</body>
</html>