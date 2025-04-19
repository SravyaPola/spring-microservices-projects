<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<h2>Login</h2>

<c:if test="${not empty errorMsg}">
      <div style="color:red;">${errorMsg}</div>
</c:if>

<form:form method="post" modelAttribute="loginDto" action="/login">
    Username: <form:input path="username" /><br/>
    Password: <form:password path="password" /><br/>
    <input type="submit" value="Login" />
</form:form>

<a href="/register">Don't have an account? Register</a>
