<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head><title>Hotel Form</title></head>
<body>
    <h2>Hotel Form</h2>
    <form:form modelAttribute="hotel" method="post" action="submitHotel">
        Name: <form:input path="name"/><br/>
        Address: <form:input path="address"/><br/>
		Contact: <form:input path="contact"/><br/>
		Email: <form:input path="email"/><br/>
		
        <input type="submit" value="Submit"/>
    </form:form>
</body>
</html>