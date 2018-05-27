<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>">
</head>
<body>
<div class="container">
    Назва модуля: ${article.name}
    <h3>Створені кадры</h3>
    <div class="row">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Назва</th>
                <th>Зміст</th>
                <th>Тип</th>
                <th>Наступний тип</th>
                <th>Наступний</th>
            </tr>
            </thead>
            <c:forEach var="cadr" items="${cadrs}">
                <tr style="background-color: ${cadr.color}">
                    <c:if test="${cadr.getClassName().equals(\"Info\")}">
                        <td><a href="/admin/info/${cadr.id}">${cadr.name}</a></td>
                    </c:if>
                    <c:if test="${cadr.getClassName().equals(\"Test\")}">
                        <td><a href="/admin/test/${cadr.id}">${cadr.name}</a></td>
                    </c:if>
                    <td>${cadr.body}</td>
                    <td>${cadr.getClassName()}</td>
                    <c:if test="${cadr.nextTest != null}">
                        <td>${cadr.nextTest.getClassName()}</td>
                    </c:if>
                    <c:if test="${cadr.nextInfo != null}">
                        <td>${cadr.nextInfo.getClassName()}</td>
                    </c:if>
                    <c:if test="${cadr.nextTest != null}">
                        <td>${cadr.nextTest.name}</td>
                    </c:if>
                    <c:if test="${cadr.nextInfo != null}">
                        <td>${cadr.nextInfo.name}</td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
        <div>
            <button class="btn default" onclick="document.getElementById('info-id').style.display='block'; document.getElementById('test-id').style.display='none';">
                Додати Інформаційний кадр
            </button>
        </div>
        <div>
            <c:if test="${!cadrs.isEmpty()}">
                <button class="btn default" onclick="document.getElementById('test-id').style.display='block'; document.getElementById('info-id').style.display='none';">
                    Додати Контрольний кадр
                </button>
            </c:if>
        </div>
        <form method="post" action="${article.id}/test" id="test-id" style="display: none; width: 100%">
            Тип:
            <div class="form-group">
                <label><input type="radio" name="type" id="type-open" value="OPEN">Відкрита відповідь</label>
                <label><input type="radio" name="type" id="type-formula" value="FORMULA">Формула</label>
            </div>
            <div class="form-group">
                <label for="name">Назва</label>
                <input type="text" class="form-control" id="name" name="name">
            </div>
            <div class="form-group">
                <label for="body">Зміст</label>
                <textarea cols="50" rows="20" name="body" id="body" class="form-control"></textarea>
            </div>
            <div class="form-group">
                <label for="condition">Необхідні умови</label>
                <input type="text" name="condition" id="condition" class="form-control">
            </div>
            <div class="form-group">
                <label for="color">Колір сторінки</label>
                <input type="color" name="color" id="color" class="form-control" value="#ffffff">
            </div>
            <input type="submit" value="Создать">
        </form>
        <form method="post" action="${article.id}/info" id="info-id" style="display: none; width: 100%">
            <div class="form-group">
                <label for="name1">Назва</label>
                <input type="text" class="form-control" id="name1" name="name">
            </div>
            <div class="form-group">
                <label for="body1">Зміст</label>
                <textarea cols="50" rows="20" name="body" id="body1" class="form-control"></textarea>
            </div>
            <div class="form-group">
                <label for="color1">Колір сторінки</label>
                <input type="color" name="color" id="color1" class="form-control" value="#ffffff">
            </div>
            <input type="submit" value="Создать">
        </form>
    </div>
</div>
</body>
</html>