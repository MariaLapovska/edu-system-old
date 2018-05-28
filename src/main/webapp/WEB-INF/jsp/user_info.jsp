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

            <c:if test="${info.nextTest != null}">
                <button><a style="text-decoration: none;!important;" href="<c:url value="/test/${info.nextTest.id}"/>">Наступний
                    кадр</a></button>
            </c:if>
            <c:if test="${info.nextInfo != null}">
                <button><a style="text-decoration: none;!important;" href="<c:url value="/info/${info.nextInfo.id}"/>">Наступний
                    кадр</a></button>
            </c:if>
        </div>
    </div>
</div>


</body>
</html>