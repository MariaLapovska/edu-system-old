<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>">
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-6">
            <form method="post" action="#" id="form-test">
                <div class="form-group">
                    <label for="login">Логін</label>
                    <input type="text" name="login" class="form-control" id="login"/>
                </div>
                <div class="form-group">
                    <label for="password">Пароль</label>
                    <input type="password" name="password" class="form-control" id="password"/>
                </div>
                <input type="submit" value="Логін">
            </form>
        </div>
    </div>
</div>
</body>
</html>