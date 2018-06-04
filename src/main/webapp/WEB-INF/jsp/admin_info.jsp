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
                <td> ${info.name}</td>
            </tr>
            <tr>
                <th>Зміст</th>
                <td>${info.body}</td>
            </tr>
            <tr>

        </table>


        <form method="post" action="#">
            <input type="submit" value="Видалити">
        </form>
        <button><a href="/admin/info/${info.id}/edit">Змінити</a></button>
    </div>
</div>
</body>
</html>