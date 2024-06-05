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

    <h2>Pagadores Ativos</h2>
    <g:each var="payer" in="${payerList}">
        <span>Nome: </span>
        <a href="${createLink(controller: 'payer', action: 'show', id: payer.id)}">${payer.name}</a><br> 
    </g:each>

    <h2>Pagadores Inativos</h2>
    <g:each var="deletedPayer" in="${deletedPayerList}">
        <span>Nome: ${deletedPayer.name}</span>
        <a href="${createLink(controller: 'payer', action: 'restore', id: deletedPayer.id)}">Restaurar</a><br>
    </g:each>
</body>
</html>