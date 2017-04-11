<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="computerId" type="java.lang.Long" description="Computer Id"%>
<%@ attribute name="computerName" type="java.lang.String" description="Computer Name"%>
<%@ attribute name="computerIntroduced" type="java.lang.String" description="Computer Introduced Date"%>
<%@ attribute name="computerDiscontinued" type="java.lang.String" description="Computer Discontinued Date"%>
<%@ attribute name="computerManufacturerName" type="java.lang.String" description="Company Name"%>

<tr>
	<td class="editMode"><input type="checkbox" name="cb"
		class="cb" value="${computerId}"></td>
	<td><a href="editComputer?id=${computerId}"
		onclick="">${computerName}</a></td>
	<td>${computerIntroduced}</td>
	<td>${computerDiscontinued}</td>
	<td>${computerManufacturerName}</td>
</tr>