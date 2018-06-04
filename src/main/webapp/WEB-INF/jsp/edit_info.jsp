<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>">
    <script src="<c:url value="/js/jquery-3.3.1.min.js"/>"></script>
</head>
<body>
<div class="container">
    <div class="row">
        <form method="post" action="#" style="width: 100%">
            <div class="form-group">
                <label for="name1">Назва</label>
                <input type="text" class="form-control" id="name1" name="name" value="${info.name}">
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
                <textarea cols="50" rows="20" name="body" id="body1" class="form-control">${info.body}</textarea>
            </div>
            <input type="submit" value="Змінити">
        </form>
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
        let image = '<p><img src="' + imageField.val() + '" alt=""/></p>';
        textArea.val(textArea.val() + image);
    }
</script>
</body>
</html>