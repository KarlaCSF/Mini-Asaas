<%@ page 
import="com.mini.asaas.utils.CpfCnpjUtils; com.mini.asaas.enums.payment.PaymentStatus"
contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
</head>

<body>
<form>
    <g:if test="${params.errorMessage}">
        <span>${params.errorMessage}</span>
    </g:if>

    <fieldset>         
        <div>
            <label for="payerId">Responsável pela cobrança</label><br>
            <input type="text" value="${payment.customer.name}" disabled><br>
            <input type="text" value="${payment.customer.email}" disabled><br>
            <input type="text" value="${CpfCnpjUtils.applyMask(payment.customer.cpfCnpj)}" disabled><br>
        </div><br>

        <div>
            <label for="payerId">Pagador</label><br>
            <input type="text" value="${payment.payer.name}" disabled><br>
            <input type="text" value="${payment.payer.email}" disabled><br>
        </div><br>

        <div>
            <label for="name">Valor da cobrança</label><br>
            <input type="number" value="${payment.value}" disabled><br>
        </div><br>

        <div>
            <label for="email">Data de Vencimento</label><br>
            <g:textField name="dueDate" value="${g.formatDate(format: 'dd/MM/yyyy', date: payment.dueDate)}" disabled="true"/>
        </div><br>

        <div>
            <label for="billingType">Tipo de Pagamento</label><br>
            <input type="text" value="${payment.billingType.getMessage()}" disabled><br>        
        </div><br>

        <div>
            <label for="status">Estado da cobrança</label><br>
            <input type="text" value="${payment.status.getMessage()}" disabled><br>
        </div><br>
        
        <g:if test="${!payment.status.isPaid()}">
            <a href="${createLink(controller: 'invoice', action: 'pay', id: payment.id)}">Pagar Cobrança</a>
        </g:if>
    </fieldset>
</form>
</body>
</html>
