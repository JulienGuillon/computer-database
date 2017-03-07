<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="body" required="true" type="java.lang.String"
	description="Body of the link"%>
<%@ attribute name="target" required="true" type="java.lang.String"
	description="Servlet to request"%>
<%@ attribute name="numOfPage" type="java.lang.Integer"
	description="Page to request"%>
<%@ attribute name="limit" type="java.lang.Integer"
	description="Number of computer per page"%>
<%@ attribute name="classes" type="java.lang.String"
	description="Classes to apply at the link"%>
<%@ attribute name="orderBy" type="java.lang.String"
	description="Order by ascending or descending"%>
<%@ attribute name="filter" type="java.lang.String"
	description="Pattern to search"%>
<%@ attribute name="lang" type="java.lang.String"
	description="Just for a change of locale"%>
<a class="${classes}" href="${target}?numOfPage=${numOfPage}&limit=${limit}&filter=${filter}">${body}</a>