<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.mini.asaas.enums.payment.BillingType; com.mini.asaas.utils.StringUtils" %>
<html>
    <head>
        <meta name="layout" content="main"/>
        <title>Editar Cobrança - Mini Asaas</title>
    </head>

    <body>
        <atlas-form action="${createLink(controller: "payment", action: "update", id: payment.id)}" method="POST">
            <atlas-layout gap="2">
                <atlas-layout row gap="4">
                    <atlas-labeled-content label="Responsável pela cobrança">
                        <atlas-input
                                name="payerName"
                                value="${payment.customer.name}"
                                disabled="true">
                        </atlas-input>
                    </atlas-labeled-content>

                    <atlas-labeled-content label="Pagador">
                        <atlas-input
                                name="payerName"
                                value="${payment.payer.name}"
                                disabled="true">
                        </atlas-input>
                    </atlas-labeled-content>

                    <atlas-labeled-content label="Insira o valor da cobrança">
                        <atlas-float-input
                                name="value"
                                value="${payment.value}">
                        </atlas-float-input>
                    </atlas-labeled-content>

                    <atlas-labeled-content label="Insira a data de vencimento da cobrança">
                        <atlas-datepicker
                                placeholder="Data de Vencimento"
                                name="dueDate"
                                value="${payment.dueDate}">
                        </atlas-datepicker>
                    </atlas-labeled-content>

                    <atlas-labeled-content label="Insira a forma de pagamento">
                        <atlas-select name="billingType" value="${params.payerId}">
                            <atlasFormTagLib:optionList
                                    name="billingType"
                                    from="${BillingType.values()}"
                                    noSelectionLabel="Selecione uma forma de pagamento"
                                    valueMessagePrefix="BillingType"
                                    value="${payment.billingType}"/>
                        </atlas-select>
                    </atlas-labeled-content>
                </atlas-layout>
                <atlas-button submit description="Editar Cobrança"></atlas-button>
        </atlas-form>
    </body>
</html>
