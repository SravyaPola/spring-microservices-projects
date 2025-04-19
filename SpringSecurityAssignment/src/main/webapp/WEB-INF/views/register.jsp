<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<h2>Register</h2>

<c:if test="${not empty error}">
    <p style="color:red">${error}</p>
</c:if>

<form:form method="post" modelAttribute="registerDto">
    Username: <form:input path="username" /><br/>
    Email: <form:input path="email" /><br/>
    Password: <form:password path="password" /><br/>
    Role:
    <form:select path="role">
        <form:option value="USER" label="USER" />
        <form:option value="ADMIN" label="ADMIN" />
    </form:select><br/>
    <input type="submit" value="Register" />
</form:form>

<a href="/login">Already have an account? Login</a>
