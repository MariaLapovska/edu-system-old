<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>">
</head>
<body>

<div class="container">
    <div class="row">
        <div class="row-12">
            <h3>Назва ${info.name}</h3>
            <h4>Зміст</h4>
            ${info.body}
            <br/>

            <c:if test="${nextId != null}">
                <c:if test="${nextId.getClassName().equals(\"Test\")}">
                    <button><a style="text-decoration: none;!important;" href="<c:url value="/test/${nextId.id}"/>">Наступний
                        кадр</a></button>
                </c:if>
                <c:if test="${nextId.getClassName().equals(\"Info\")}">
                    <button><a style="text-decoration: none;!important;" href="<c:url value="/info/${nextId.id}"/>">Наступний
                        кадр</a></button>
                </c:if>
            </c:if>
        </div>
    </div>
</div>


</body>
</html>