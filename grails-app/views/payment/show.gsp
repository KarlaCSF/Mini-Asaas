<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
</head>

<body>
<form>

    <fieldset>
        <legend>ID da cobrança: ${payment.id}</legend>
            
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
                <label for="paymentStatus">Estado da cobrança</label><br>
                <input type="text" value="${payment.paymentStatus.getMessage()}" disabled><br>
            </div><br>

            <a href="${createLink(controller: 'payment', action: 'edit', id: payment.id)}">Editar Cobrança</a>
            <a href="">Excluir Cobrança</a>
        </fieldset>
     
    </fieldset>

</form>
</body>
</html>