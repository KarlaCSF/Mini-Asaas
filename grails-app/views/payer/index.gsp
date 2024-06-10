<%@ page
contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Pagadores</title>
</head>

<body>
    <g:if test="${params.errorMessage}">
        <span>${params.errorMessage}</span>
    </g:if>

    <h1>Pagadores</h1>
    <a href="${createLink(controller: 'payer', action: 'create')}">Criar Pagador</a><br>
    <a href="${createLink(controller: 'payer', action: 'list')}">Listar Pagadores</a>
</body>
</html>
