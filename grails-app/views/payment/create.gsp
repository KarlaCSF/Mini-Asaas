<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.mini.asaas.utils.StringUtils" %>
<%@ page import="com.mini.asaas.enums.payment.BillingType" %>

<html>
    <head>
        <meta name="layout" content="main"/>
        <title>Criar Cobrança - Mini Asaas</title>
    </head>

    <body>
        <g:if test="${params.errorMessage}">
            <span>${params.errorMessage}</span>
        </g:if>

        <atlas-form action="${createLink(controller: "payment", action: "save")}" method="POST">
            <atlas-layout gap="2">
                <atlas-layout row gap="4">
                    <atlas-labeled-content label="Selecione o pagador">
                        <atlas-select name="payerId" value="${params.payerId}" required="true">
                            <atlasFormTagLib:optionList
                                    from="${listPayersByCustomer}"
                                    noSelectionLabel="Escolha o pagador"
                                    optionKey="id"
                                    optionValue="name"/>
                        </atlas-select>
                    </atlas-labeled-content>

                    <atlas-labeled-content label="Insira o valor da cobrança">
                        <atlas-money
                                name="value"
                                value="params.value"
                                required="true">
                        </atlas-money>
                    </atlas-labeled-content>

                    <atlas-labeled-content label="Insira a data de vencimento da cobrança">
                        <atlas-datepicker
                                placeholder="Data de Vencimento"
                                name="dueDate"
                                value="${params.dueDate}"
                                required="true">
                        </atlas-datepicker>
                    </atlas-labeled-content>

                    <atlas-labeled-content label="Insira a forma de pagamento">
                        <atlas-select name="billingType" value="${params.payerId}" required="true">
                            <atlasFormTagLib:optionList
                                    name="billingType"
                                    from="${BillingType.values()}"
                                    noSelectionLabel="Selecione uma forma de pagamento"
                                    valueMessagePrefix="BillingType"
                                    value="${params.billingType}"/>
                        </atlas-select>
                    </atlas-labeled-content>
                </atlas-layout>
                <atlas-button submit description="Criar Cobrança"></atlas-button>
        </atlas-form>
    </body>
</html>
