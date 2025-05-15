<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>File Upload and Download</title>
</head>
<body>
<h2>Upload File</h2>

<form method="POST" enctype="multipart/form-data" action="/upload">
    <input type="file" name="file" required />
    <button type="submit">Upload</button>
</form>

<br/>
<h2>Download Files</h2>
<ul>
    <c:forEach var="file" items="${files}">
        <li><a href="/download/${file}">${file}</a></li>
    </c:forEach>
</ul>

</body>
</html>
