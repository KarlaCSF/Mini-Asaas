<%@ page import="com.mini.asaas.enums.payment.BillingType; com.mini.asaas.utils.StringUtils; com.mini.asaas.utils.DateUtils" contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <meta name="layout" content="main"/>
        <title>Exibir Cobrança - Mini Asaas</title>
    </head>

    <body>
        <atlas-section header="Cobranças">
            <atlas-form-panel header="Cobrança: ${payment.id}">
            <atlas-button
                    slot="actions"
                    href="${createLink(controller: 'payment', action: 'edit', id: payment.id)}"
                    description="Editar"
                    icon="pencil"
                    data-panel-start-editing
                ${!payment.canEdit() ? "disabled" : ""}>
            </atlas-button>
            <atlas-button
                    slot="actions"
                    href="${createLink(controller: 'payment', action: 'delete', id: payment.id)}"
                    description="Excluir"
                    icon="trash"
                    data-panel-start-editing
                    theme="danger"
                ${!payment.canEdit() ? "disabled" : ""}>
            </atlas-button>
            <atlas-layout gap="2">
                <atlas-layout row gap="4">
                    <atlas-input
                            label="Responsável pela cobrança"
                            name="customerName"
                            value="${payment.customer.name}"
                            disabled="true"
                            required="true">
                    </atlas-input>

                    <atlas-input
                            label="Pagador"
                            name="payerName"
                            value="${payment.payer.name}"
                            disabled="true"
                            required="true">
                    </atlas-input>

                    <atlas-money
                            label="Valor da cobrança"
                            name="value"
                            value="${StringUtils.formatCurrencyWithoutSymbol(payment.value.toString())}"
                            required="true">
                    </atlas-money>

                    <atlas-datepicker
                            label="Data de Vencimento"
                            name="dueDate"
                            value="${DateUtils.formatDate(payment.dueDate)}"
                            required="true">
                    </atlas-datepicker>

                    <atlas-input label="Forma de pagamento"
                                 name="billingType"
                                 value="${payment.billingType.getMessage()}"
                                 required="true">
                    </atlas-input>

                    <atlas-input label="Estado da Cobrança"
                                 name="status"
                                 value="${payment.status.getMessage()}"
                                 required="true">
                    </atlas-input>

                    <atlas-layout inline gap="2">
                        <atlas-button
                                href="${createLink(controller: 'payment', action: 'pay', id: payment.id)}"
                                description="Confirmar pagamento em dinheiro"
                                theme="success"
                            ${!payment.canEdit() ? "disabled" : ""}>
                        </atlas-button>

                        <atlas-button
                                href="${createLink(controller: 'payment', action: 'list')}"
                                description="Voltar"
                                theme="secondary">
                        </atlas-button>
                    </atlas-layout>
                </atlas-layout>
        </atlas-form-panel>
        </atlas-section>
    </body>
</html>