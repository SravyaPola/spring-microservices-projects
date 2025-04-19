<!DOCTYPE html>
<html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
   <head>
      <meta charset = "ISO-8859-1"/>
      <title>Internationalization</title>
   </head>
   <body>
      <h1><spring:message code="common.hello" /></h1></br>
      <spring:message code="common.name" /><input type='text'/>
   </body>
</html>