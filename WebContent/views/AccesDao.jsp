<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@page import="com.excilys.computerdatabase.dao.impl.CrudCompanyImpl"%>   
	<%@page import="com.excilys.computerdatabase.entities.Company"%>   
	<jsp:useBean id="obj" class="com.excilys.computerdatabase.entities.Company"/>  
	  
	<jsp:setProperty property="*" name="obj"/>  
	  
	<% 
	CrudCompanyImpl crud = new CrudCompanyImpl();
	Company company = crud.find(3).get();
 	out.println(company.getName());
	  
	%>  

</body>
</html>