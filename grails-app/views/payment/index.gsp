<%@ page contentType="text/html;charset=UTF-8" %>
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
            
        <fieldset>         
            <div>
                <label for="payerId">Escolha o Pagador</label><br>
                <g:select name="payerId" from="${payerList}" optionKey="id" optionValue="name" noSelection="['':'Selecione um pagador']"/><br>
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
                <label for="paymentType">Tipo de Pagamento</label><br>
                <g:select name="paymentType" from="${paymentTypes}" noSelection="['':'Selecione uma forma de pagamento']"/>
            </div><br>
        </fieldset>
        
        <input type="submit" value="Cadastrar">
    </fieldset>
</form>
</body>
</html>