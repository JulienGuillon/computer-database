<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="./css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="./css/font-awesome.css" rel="stylesheet" media="screen">
<link href="./css/main.css" rel="stylesheet" media="screen">
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
                    <h1>Add Computer</h1>
                    <form action="addComputer" method="POST">
                    <input name="action" type="hidden" value="add" />
                    	<fieldset>
                            <div class="form-group">
                                <label for="computerName">Computer name</label>
                                <input type="text" class="form-control" id="computerName" placeholder="Computer name" name="computerName">
                                <span id="nameError" class="error" style="display:none">A valid name is required !</span>
                                
                            </div>
                            <div class="form-group">
                                <label for="introduced">Introduced date</label>
                                <input type="date" class="form-control" id="introduced" placeholder="Introduced date" name="introduced">
                            </div>
                            <div class="form-group">
                                <label for="discontinued">Discontinued date</label>
                                <input type="date" class="form-control" id="discontinued" placeholder="Discontinued date" name="discontinued">
                                <span id="dateError" class="error" style="display:none">Discontinued date can't be before introduced date !</span>
                                
                            </div>
                            
                            <div class="form-group">
								<label for="companyId">Company</label>
								<select name="companyId" class="form-control" id="companyId">
									<option value="0"></option>
									<c:forEach items="${companies}" var="company">										
										<option value="${company.id}">${company.name}</option>
									</c:forEach>
								</select>
							</div>          
                        </fieldset>
                        <div class="actions pull-right">
	                        <input id="validAdd" type="submit" value="Add" class="btn btn-primary disabled">
                            or
                            <a href="addComputer" class="btn btn-default">Cancel</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
    <script src="./js/jquery.min.js"></script>
    <script src="./js/addComputer.js"></script>
</body>
</html>