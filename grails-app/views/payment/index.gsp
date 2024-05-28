<%@ page 
import="com.mini.asaas.Payer"
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
</body>
</html>
