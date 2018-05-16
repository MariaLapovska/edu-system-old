<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
</head>
<body>

Назва чего-то: ${test.name}
${test.body}
${test.condition}
${test.testType}

<form method="post" action="#">
    <input type="submit" value="Видалити">
</form>

</body>
</html>