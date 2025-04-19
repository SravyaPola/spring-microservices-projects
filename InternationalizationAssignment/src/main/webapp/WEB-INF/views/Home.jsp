<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>
    <title>Internationalization</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(document).ready(function () {
            $('.my_button').click(function () {
                const selectedLang = $(this).val();
                window.location.href = '/home?lang=' + selectedLang;
            });
        });
    </script>
</head>
<body>
    Name: (<spring:message code="common.name" />) <input type='text'/><br/>
    Country: (<spring:message code="common.name" />) <input type='text'/><br/>
    Age: (<spring:message code="common.name" />) <input type='text'/><br/>

    <button class="my_button" type="button" value="en">English</button>
    <button class="my_button" type="button" value="fr">French</button>
    <button class="my_button" type="button" value="de">German</button>
</body>
</html>
