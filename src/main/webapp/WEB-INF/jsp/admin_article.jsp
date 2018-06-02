<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>">
    <script src="<c:url value="/js/jquery-3.3.1.min.js"/>"></script>
</head>
<body>
<div class="container">
    Назва модуля: ${article.name}
    <h3>Створені кадры</h3>
    <div class="row">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Номер</th>
                <th>Назва</th>
                <th>Зміст</th>
                <th>Тип</th>
            </tr>
            </thead>
            <c:forEach var="cadr" items="${cadrs}">
                <tr>
                    <td>${cadr.id}</td>
                    <c:if test="${cadr.getClassName().equals(\"Info\")}">
                        <td><a href="/admin/info/${cadr.id}">${cadr.name}</a></td>
                    </c:if>
                    <c:if test="${cadr.getClassName().equals(\"Test\")}">
                        <td><a href="/admin/test/${cadr.id}">${cadr.name}</a></td>
                    </c:if>
                    <td>${cadr.body}</td>
                    <td>${cadr.getClassName()}</td>
                </tr>
            </c:forEach>
        </table>
        <div>
            <button class="btn default"
                    onclick="document.getElementById('info-id').style.display='block'; document.getElementById('test-id').style.display='none';">
                Додати Інформаційний кадр
            </button>
        </div>
        <div>
            <c:if test="${!cadrs.isEmpty()}">
                <button class="btn default"
                        onclick="document.getElementById('test-id').style.display='block'; document.getElementById('info-id').style.display='none';">
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
            <input type="submit" value="Создать">
        </form>
        <form method="post" action="${article.id}/info" id="info-id" style="display: none; width: 100%">
            <div class="form-group">
                <label for="name1">Назва</label>
                <input type="text" class="form-control" id="name1" name="name">
            </div>
            <div class="form-group">
                <a href="##" onclick="wrapText('body1', '<b>', '</b>')">Жирний текст</a>
                <a href="##" onclick="wrapText('body1', '<i>', '</i>')">Курсив</a>
                <a href="##" onclick="document.getElementById('img-src-div').style.display='block';">Додати картинку</a>
                <div id="img-src-div" style="display: none">
                    <input type="text" id="img-src">
                    <a href="##"
                       onclick="wrapImg('body1', 'img-src'); document.getElementById('img-src').value = '';document.getElementById('img-src-div').style.display='none'">Додати</a>
                </div>
                <a href="##" onclick="document.getElementById('color-src-div').style.display='block';">Додати колір</a>
                <div id="color-src-div" style="display: none">
                    <input type="color" id="color-src" value="#ffffff">
                    <a href="##"
                       onclick="wrapColor('body1', 'color-src'); document.getElementById('color-src').value = '#ffffff';document.getElementById('color-src-div').style.display='none'">Додати</a>
                </div>
                <label for="body1">Зміст</label>
                <textarea cols="50" rows="20" name="body" id="body1" class="form-control"></textarea>
            </div>
            <input type="submit" value="Создать">
        </form>

        <c:if test="${cadrs.size() >= 2}">
            <form method="post" action="/admin/link/${article.id}" style="width: 100%">
                Тип:
                <div class="form-group">
                    <label><input type="radio" name="type" id="type-str" value="STRAIGHT">Прямий перехід</label>
                    <label><input type="radio" name="type" id="type-rev" value="REVERSE">Зворотній перехід</label>
                </div>
                <div class="form-group">
                    <label for="from">Базовий</label>
                    <input type="number" class="form-control" id="from" name="from">
                </div>
                <div class="form-group">
                    <label for="to">Зв'язати з</label>
                    <input type="number" name="to" id="to" class="form-control">
                </div>
                <input type="submit" value="Создать">
            </form>
        </c:if>
    </div>
</div>
<script>
    function wrapText(elementID, openTag, closeTag) {
        let textArea = $('#' + elementID);
        let len = textArea.val().length;
        let start = textArea[0].selectionStart;
        let end = textArea[0].selectionEnd;
        if (start === end) {
            return false;
        }
        let selectedText = textArea.val().substring(start, end);
        let replacement = openTag + selectedText + closeTag;
        textArea.val(textArea.val().substring(0, start) + replacement + textArea.val().substring(end, len));
    }

    function wrapColor(elementID, colorID) {
        let textArea = $('#' + elementID);
        let color = $('#' + colorID).val();
        let len = textArea.val().length;
        let start = textArea[0].selectionStart;
        let end = textArea[0].selectionEnd;
        if (start === end) {
            return false;
        }
        let selectedText = textArea.val().substring(start, end);
        let replacement = '<span style="color: ' + color + ';">' + selectedText + '</span>';
        // let replacement = '<span style="color: \'' + color + '"\'>' + selectedText + '</span>';
        textArea.val(textArea.val().substring(0, start) + replacement + textArea.val().substring(end, len));
    }

    function wrapImg(textAreaID, imgID) {
        let textArea = $('#' + textAreaID);
        let imageField = $('#' + imgID);
        let image = '<img src="' + imageField.val() + '" alt=""/>';
        textArea.val(textArea.val() + image);
    }
</script>
</body>
</html>