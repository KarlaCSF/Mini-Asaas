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
<form action="${createLink(controller: "payment", action: "save")}" method="POST">

    <fieldset>
        <legend>Criar Cobrança</legend>
        
        <g:if test="${params.errorMessage}">
            <span>${params.errorMessage}</span>
        </g:if>
        
        <g:if test="${params.successfulMessage}">
            <span>${params.successfulMessage}</span>
        </g:if>
            
        <fieldset>         
            <div>
                <label for="payerId">Escolha o Pagador</label><br>
                <g:select name="payerId" from="${listPayersByCustomer}" optionKey="id" optionValue="name" noSelection="['':'Selecione um pagador']" required="true"/><br>
            </div><br>

            <div>
                <label for="name">Valor da cobrança</label><br>
                <input type="number" placeholder="Insira o valor da cobrança" name="value" value="${params.value}" id="value" min="0.00" step="0.01" required><br>
            </div><br>

            <div>
                <label for="email">Data de Vencimento</label><br>
                <input type="date" placeholder="Insira a data de vencimento" name="dueDate" value="${params.dueDate}" id="dueDate" required><br>
            </div><br>
 
            <div>
                <label for="billingType">Tipo de Pagamento</label><br>
                <g:select name="billingType" from="${BillingType.values()}" valueMessagePrefix="BillingType" noSelection="['':'Selecione uma forma de pagamento']" required="true"/>
            </div><br>
        </fieldset>
        
        <input type="submit" value="Criar Cobrança">
    </fieldset>
</form>
</body>
</html>
