<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>">
</head>
<body>
<div class="container">
    Назва категорії: ${category.name}
    <h3>Створені модулі</h3>
    <div class="row">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Назва</th>
            </tr>
            </thead>
            <c:forEach var="article" items="${articles}">
                <tr>
                    <td><a href="/admin/article/${article.id}">${article.name}</a></td>
                </tr>
            </c:forEach>
        </table>
        <form method="post" action="#">
            <div class="form-group">
                <label for="name">Назва</label>
                <input type="text" id="name" name="name" class="form-control">
            </div>
            <div class="form-group">
                <label for="body">Зміст</label>
                <textarea id="body" cols="100" rows="10" name="body" class="form-control"></textarea>
            </div>
            <input type="submit" value="Створити">
        </form>
    </div>
</div>
</body>
</html>