<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.mini.asaas.payer.Payer" %>
<%@ page import="com.mini.asaas.enums.payment.BillingType" %>
<html>
    <head>
        <meta name="layout" content="main"/>
        <title>Cobrança - Mini Asaas</title>
    </head>

    <body>
        <h2>Cobranças</h2>
        <a href="${createLink(controller: 'payment', action: 'create')}">Criar Cobrança</a>
        <a href="${createLink(controller: 'payment', action: 'list')}">Listar Cobranças</a>
    </body>
</html>
