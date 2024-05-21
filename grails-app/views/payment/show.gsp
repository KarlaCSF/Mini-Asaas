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
                <label for="paymentType">Tipo de Pagamento</label><br>
                <input type="text" value="${payment.paymentType}" disabled><br>
            </div><br>
        </fieldset>
     
    </fieldset>

</form>
</body>
</html>