<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Surveyor Form</title>

<style>

	.error {
		color: #ff0000;
	}
</style>

</head>

<body>

	<h2>Surveyor Form</h2>
 
	<form:form method="POST" modelAttribute="surveyor">
		<form:input type="hidden" path="id" id="id"/>
		<table>
                    <tr>
				<td><label for="site">Site Name: </label> </td>
				<td><form:input path="site" id="site"/></td>
				<td><form:errors path="site" cssClass="error"/></td>
		    </tr>
			<tr>
				<td><label for="name">Developer Name: </label> </td>
				<td><form:input path="name" id="name"/></td>
				<td><form:errors path="name" cssClass="error"/></td>
		    </tr>
	    
			<tr>
				<td><label for="joiningDate">Joining Date: </label> </td>
				<td><form:input path="joiningDate" id="joiningDate"/></td>
				<td><form:errors path="joiningDate" cssClass="error"/></td>
		    </tr>
	
			<tr>
				<td><label for="cost">Cost: </label> </td>
				<td><form:input path="cost" id="cost"/></td>
				<td><form:errors path="cost" cssClass="error"/></td>
		    </tr>
	
			
	
			<tr>
				<td colspan="3">
					<c:choose>
						<c:when test="${edit}">
							<input type="submit" value="Update"/>
						</c:when>
						<c:otherwise>
							<input type="submit" value="Submit"/>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</table>
	</form:form>
	<br/>
	<br/>
	Go back to <a href="<c:url value='/list' />">List of All Surveyors</a>
</body>
</html>