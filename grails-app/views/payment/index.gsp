<%@ page 
import="com.mini.asaas.payer.Payer"
import="com.mini.asaas.enums.payment.BillingType"
contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
    <html lang="pt-br">
</head>

<body>
    <h2>Payments</h2>
    <a href="${createLink(controller: 'payment', action: 'create')}">Criar Cobrança</a>
    <a href="${createLink(controller: 'payment', action: 'list')}">Listar Cobranças</a>
</body>
</html>
