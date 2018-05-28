<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>">
</head>
<body>

<div class="container">
    <div class="row">
        <table class="table">
            <tr>
                <th>Назва</th>
                <td> ${test.name}</td>
            </tr>
            <tr>
                <th>Зміст</th>
                <td>${test.body}</td>
            </tr>
            <c:if test="${resultPresent}">
                <tr>
                    <th>Результат</th>
                    <td> ${result}</td>
                </tr>
            </c:if>
        </table>
        <c:if test="${!resultPresent}">
            <div class="col-6">
                <form method="post" action="#" id="form-test">
                    <div class="form-group">
                        <label for="payload">Відповідь</label>
                        <textarea name="payload" class="form-control" id="payload" rows="10" cols="50"></textarea>
                    </div>
                    <input type="submit" value="Перевірити">
                </form>
            </div>
        </c:if>
        <c:if test="${resultPresent && result && test.nextTest != null}">
            <button><a style="text-decoration: none;!important;" href="<c:url value="/test/${test.nextTest.id}"/>">Наступний кадр</a></button>
        </c:if>
        <c:if test="${resultPresent && result && test.nextInfo != null}">
            <button><a style="text-decoration: none;!important;" href="<c:url value="/info/${test.nextInfo.id}"/>">Наступний кадр</a></button>
        </c:if>
        <c:if test="${resultPresent && !result}">
            <button><a style="text-decoration: none;!important;" href="<c:url value="/info/${lastInfoId}"/>">Повернутися на попередній інформаційний кадр</a></button>
        </c:if>
    </div>
</div>


</body>
</html>