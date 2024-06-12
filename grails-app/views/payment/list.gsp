<%@ page
contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Cobranças</title>
</head>

<body>
    <g:if test="${params.errorMessage}">
            <span>${params.errorMessage}</span>
    </g:if>

    <h2>Cobranças Ativas</h2>
    <g:each var="payment" in="${paymentList}">
        <span>Cobrança: </span>
        <a href="${createLink(controller: 'payment', action: 'show', id: payment.id)}">${payment.id} R$ ${payment.value}</a><br> 
    </g:each>

    <h2>Cobranças Inativas</h2>
    <g:each var="deletedPayment" in="${deletedPaymentList}">
        <span>Cobrança: ${deletedPayment.id} R$ ${deletedPayment.value}</span>
        <a href="${createLink(controller: 'payment', action: 'restore', id: deletedPayment.id)}">Restaurar</a><br>
    </g:each>
</body>
</html>
