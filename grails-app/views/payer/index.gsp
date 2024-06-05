<%@ page
contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Pagadores</title>
</head>

<body>
    <h1>Pagadores</h1>
    <a href="${createLink(controller: 'payer', action: 'create')}">Criar Pagador</a><br>
    <a href="${createLink(controller: 'payer', action: 'list')}">Listar Pagadores</a>
</body>
</html>