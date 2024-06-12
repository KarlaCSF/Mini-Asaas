<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.mini.asaas.utils.DateUtils; com.mini.asaas.utils.StringUtils" %>
<%@ page import="com.mini.asaas.enums.payment.BillingType" %>

<html>
    <head>
        <meta name="layout" content="main"/>
        <title>Criar Cobrança - Mini Asaas</title>
    </head>

    <body>
        <atlas-panel header="Criando Cobrança">
            <g:if test="${params.errorMessage}">
                <span>${params.errorMessage}</span>
            </g:if>

            <atlas-form action="${createLink(controller: "payment", action: "save")}" method="post">
                <atlas-layout row gap="4">
                    <atlas-select label="Selecione o pagador" name="payerId" value="${params.payerId}" required="true">
                        <atlasFormTagLib:optionList
                                from="${listPayersByCustomer}"
                                noSelectionLabel="Escolha o pagador"
                                optionKey="id"
                                optionValue="name"
                        />
                    </atlas-select>

                    <atlas-money
                            label="Insira o valor da cobrança"
                            name="value"
                            value="params.value"
                            min-value="1,00"
                            min-value-error-message="O valor mínimo é de R$ 1,00"
                            required="true">
                    </atlas-money>

                    <atlas-datepicker
                            label="Insira a data de vencimento da cobrança"
                            placeholder="Data de Vencimento"
                            name="dueDate"
                            value="${params.dueDate}"
                            min-date="${DateUtils.formatDate(new Date())}"
                            required="true"
                            prevent-past-date>
                    </atlas-datepicker>

                    <atlas-select label="Insira a forma de pagamento" name="billingType" value="${params.payerId}" required="true">
                        <atlasFormTagLib:optionList
                                name="billingType"
                                from="${BillingType.values()}"
                                noSelectionLabel="Selecione uma forma de pagamento"
                                valueMessagePrefix="BillingType"
                                value="${params.billingType}"
                        />
                    </atlas-select>

                    <atlas-button submit
                                  description="Criar Cobrança">
                    </atlas-button>
                </atlas-layout>
            </atlas-form>
        </atlas-panel>
    </body>
</html>
