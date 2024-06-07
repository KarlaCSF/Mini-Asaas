<%@ page 
import="com.mini.asaas.enums.payment.BillingType"
contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
    <html lang="pt-br">
</head>

<body>
<form action="${createLink(controller: "payment", action: "update", id: payment.id)}" method="POST">

    <fieldset>
        <legend>Editar Cobrança: ${payment.id}</legend>
        
        <g:if test="${params.errorMessage}">
            <span>${params.errorMessage}</span>
        </g:if>
            
        <fieldset>         
            <div>
                <label for="payerId">Responsável pela cobrança</label><br>
                <input type="text" value="${payment.customer.name}" disabled><br>
            </div><br>

            <div>
                <label for="payerId">Pagador</label><br>
                <input type="text" value="${payment.payer.name}" disabled><br>
            </div><br>

            <div>
                <label for="name">Valor da cobrança</label><br>
                <input type="number" placeholder="Insira o valor da cobrança" name="value" value="${payment.value}" id="value" min="0.00" step="0.01" required><br>
            </div><br>

            <div>
                <label for="email">Data de Vencimento</label><br>
                <input type="date" name="dueDate" value="${payment.dueDate.toLocalDateTime().toLocalDate()}" id="dueDate" required="required"  >
            </div><br>
 
            <div>
                <label for="billingType">Tipo de Pagamento</label><br>
                <g:select name="billingType" from="${BillingType.values()}" valueMessagePrefix="BillingType" noSelection="['':'Selecione uma forma de pagamento']" value="${payment.billingType}" required="true"/>           
            </div><br>
        </fieldset>

        <input type="submit" value="Editar Cobrança">
    </fieldset>
</form>
</body>
</html>