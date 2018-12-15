<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Surveyors Details</title>

	<style>
		tr:first-child{
			font-weight: bold;
			background-color: #C6C9C4;
		}
	</style>

</head>


<body>
	<h2>List of Surveyors</h2>	
	<table>
		<tr>
			<td>Site Name</td><td>Developer Name</td><td>Start Date</td><td>Cost</td><td></td>
		</tr>
		<c:forEach items="${surveyors}" var="surveyor">
			<tr>
                            <td><a href="<c:url value='/edit-${surveyor.site}-surveyor' />">${surveyor.site}</a></td>
			<td>${surveyor.name}</td>
			<td>${surveyor.joiningDate}</td>
			<td>${surveyor.cost}</td>
			
			<td><a href="<c:url value='/delete-${surveyor.site}-surveyor' />">delete</a></td>
			</tr>
		</c:forEach>
	</table>
	<br/>
	<a href="<c:url value='/new' />">Add New Surveyor</a>
</body>
</html>