<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.mini.asaas.utils.DateUtils; com.mini.asaas.enums.payment.BillingType; com.mini.asaas.utils.StringUtils" %>
<html>
    <head>
        <meta name="layout" content="main"/>
        <title>Editar Cobrança - Mini Asaas</title>
    </head>

    <body>
        <atlas-panel header="Editando Cobrança">
            <atlas-form action="${createLink(controller: "payment", action: "update", id: payment.id)}" method="POST">
                <atlas-layout row gap="4">
                    <atlas-input label="Responsável pela cobrança"
                                 name="customerName"
                                 value="${payment.customer.name}"
                                 disabled="true"
                                 required="true">
                    </atlas-input>

                    <atlas-input label="Pagador"
                                 name="payerName"
                                 value="${payment.payer.name}"
                                 disabled="true"
                                 required="true">
                    </atlas-input>

                    <atlas-money label="Insira o valor da cobrança"
                                 name="value"
                                 min-value="10"
                                 min-value-error-message="O valor mínimo é de R$ 1,00"
                                 value="${StringUtils.formatCurrencyWithoutSymbol(payment.value.toString())}">
                    </atlas-money>

                    <atlas-datepicker label="Insira a data de vencimento da cobrança"
                                      placeholder="Data de Vencimento"
                                      name="dueDate"
                                      min-date="${DateUtils.formatDate(new Date())}"
                                      value="${DateUtils.formatDate(payment.dueDate)}"
                                      prevent-past-date>
                    </atlas-datepicker>

                    <atlas-select label="Insira a forma de pagamento"
                                  name="billingType"
                                  value="${payment.billingType}">
                        <atlasFormTagLib:optionList name="billingType"
                                                    from="${BillingType.values()}"
                                                    noSelectionLabel="Selecione uma forma de pagamento"
                                                    valueMessagePrefix="BillingType"
                        />
                    </atlas-select>
                    <atlas-layout inline gap="2" col="2">
                        <atlas-button
                                submit
                                description="Salvar Cobrança">
                        </atlas-button>
                        <atlas-button
                                href="${createLink(controller: 'payment', action: 'show', id: payment.id)}"
                                description="Cancelar"
                                theme="secondary">
                        </atlas-button>
                    </atlas-layout>
                </atlas-layout>
            </atlas-form>
        </atlas-panel>
    </body>
</html>
