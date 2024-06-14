<%@ page import="com.mini.asaas.repositories.PayerRepository; com.mini.asaas.customer.Customer; com.mini.asaas.enums.payment.PaymentStatus; com.mini.asaas.repositories.PaymentRepository" %>
<% Customer currentUserCustomer = Customer.get(1) %>
<% int countWaitingPayments = PaymentRepository.countByCustomerIdAndStatus(currentUserCustomer.id, PaymentStatus.WAITING); %>
<% int countOverduePayments = PaymentRepository.countByCustomerIdAndStatus(currentUserCustomer.id, PaymentStatus.OVERDUE); %>
<% int countPaidPayments = PaymentRepository.countByCustomerIdAndStatus(currentUserCustomer.id, PaymentStatus.PAID); %>
<% int countPayersActive = PayerRepository.countByCustomerId(currentUserCustomer.id); %>
<% int countPayersDeleted = PayerRepository.countByCustomerId(currentUserCustomer.id, true); %>

<!doctype html>
<html>
    <head>
        <meta name="layout" content="main"/>
        <title>Resumo - Mini Asaas</title>
    </head>

    <body>
        <g:if test="${flash.message}">
            <atlas-alert message="${flash.message}" type="${flash.type}"></atlas-alert>
        </g:if>

        <atlas-section header="Resumo">
            <atlas-card>
                <atlas-layout gap="4" inline alignment="top" justify="center">
                    <atlas-empty-state illustration="airplane-check-success">
                        <atlas-heading size="h4">Seja bem vindo!</atlas-heading>
                    </atlas-empty-state>
                </atlas-layout>
            </atlas-card>

            <atlas-layout inline gap="9" alignment="top" justify="center">
                <atlas-table-col>
                    <atlas-panel gap="3">
                        <atlas-layout gap="3">
                            <atlas-layout gap="4" inline>
                                <atlas-icon name="user" size="3x"></atlas-icon>
                                <atlas-heading size="h5">Pagadores</atlas-heading>
                            </atlas-layout>

                            <atlas-layout gap="4" align="center">
                                <atlas-text size="lg" theme="success" bold>Ativos</atlas-text>
                                <atlas-heading size="h2" theme="success" bold>${countPayersActive}</atlas-heading>
                                <atlas-text size="lg" theme="warning" bold>Desativados</atlas-text>
                                <atlas-heading size="h2" theme="warning" bold>${countPayersDeleted}</atlas-heading>
                            </atlas-layout>
                        </atlas-layout>
                    </atlas-panel>
                </atlas-table-col>

                <atlas-table-col>
                    <atlas-panel gap="3">
                        <atlas-layout gap="3">
                            <atlas-layout gap="4" inline>
                                <atlas-icon name="money" size="3x"></atlas-icon>
                                <atlas-heading size="h5">Cobranças</atlas-heading>
                            </atlas-layout>

                            <atlas-layout gap="4" align="center">
                                <atlas-text size="lg" theme="warning" bold>Previstas</atlas-text>
                                <atlas-heading size="h2" theme="warning" bold>${countWaitingPayments}</atlas-heading>

                                <atlas-text size="lg" theme="secondary" bold>Vencidas</atlas-text>
                                <atlas-heading size="h2" theme="secondary" bold>${countOverduePayments}</atlas-heading>

                                <atlas-text size="lg" theme="success" bold>Recebidas</atlas-text>
                                <atlas-heading size="h2" theme="success" bold>${countPaidPayments}</atlas-heading>

                            </atlas-layout>
                        </atlas-layout>
                    </atlas-panel>
                </atlas-table-col>

            </atlas-table-body>
            </atlas-layout>
        </atlas-section>
    </body>
</html>
