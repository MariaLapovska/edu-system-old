<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
</head>
<body>

Назва чего-то: ${test.name}
${test.body}

${result}
<form method="post" action="#">
    <input type="text" name="payload"/>
    <input type="submit" value="Перевірити">
</form>

</body>
</html>