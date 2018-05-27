<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>">
</head>
<body>
<div class="container">
    <h3>Головна сторінка</h3>
    <div class="row">
        <div class="col-12">
            Створені предмети
            <c:forEach var="category" items="${categories}">
                <div>
                    <a href="/admin/category/${category.id}">${category.name}</a>
                </div>
            </c:forEach>
        </div>
        <div class="col-3">
            Створити новий предмет
        <form method="post" action="#">
            <div class="form-group">
                <label for="name">Назва</label>
                <input type="text" id="name" name="name" class="form-control">
            </div>
            <input type="submit" value="Создать">
        </form>
        </div>
    </div>
</div>
</body>
</html>