<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<h2>Welcome, ${username}</h2>

<c:if test="${not empty error}">
    <p style="color:red">${error}</p>
</c:if>

	<form:form method="post" modelAttribute="roleForm">
		Login As: 
	    <input type="submit" name="roleChoice" value="USER" />
	    <input type="submit" name="roleChoice" value="ADMIN" />
	</form:form>

