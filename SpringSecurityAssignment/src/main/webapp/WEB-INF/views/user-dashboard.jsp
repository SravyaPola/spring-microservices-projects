<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h2>User Dashboard</h2>
<p>Hi, ${name} .You are logged in as user.</p>
<form action="<c:url value='/logout-success' />" method="post" style="display:inline;">
    <button type="submit">Logout</button>
</form>



