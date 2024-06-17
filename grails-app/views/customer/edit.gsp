<%@ page 
import="com.mini.asaas.enums.States"
contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
</head>
<body>
    <g:if test="${flash.message}">
        <atlas-alert message="${flash.message}" type="${flash.type}"></atlas-alert>
    </g:if>

    <atlas-form-panel header="${customer.name}" action="${createLink(controller: "customer", action: "update")}" method="POST">
        <atlas-button 
            slot="actions" 
            description="Editar" 
            icon="pencil"
            data-panel-start-editing="true">
        </atlas-button>

        <atlas-layout gap="4">
            <atlas-panel header="Dados da Conta">
                <atlas-input
                    label="Nome"
                    placeholder="Ex: João da Silva"
                    name="name"
                    value="${customer.name}"
                    required="true">
                </atlas-input>

                <atlas-input
                    label="Email"
                    placeholder="Ex: joao.silva@gmail.com"
                    name="email"
                    value="${customer.email}"
                    required="true">
                </atlas-input>

                <atlas-masked-input
                    label="CPF/CNPJ"
                    placeholder="000.000.000-00"
                    name="cpfCnpj"
                    mask-alias="cpf-cnpj"
                    value="${customer.cpfCnpj}"
                    required="true">
                </atlas-masked-input>

                <atlas-labeled-content label="Tipo de Pessoa">
                    <atlas-heading 
                        theme="primary" 
                        size="h6">
                        ${customer.personType.getLabel()}
                    </atlas-heading>
                </atlas-labeled-content>
            </atlas-panel>

            <atlas-panel header="Endereço">
                <atlas-input
                    label="CEP"
                    placeholder="00000-000"
                    name="cep"
                    value="${customer.address.cep}"
                    required="true">
                </atlas-input>

                <atlas-input
                    label="Cidade"
                    placeholder="Ex: Joinville"
                    name="city"
                    value="${customer.address.city}"
                    required="true">
                </atlas-input>

                <atlas-select
                    label="Estado"
                    name="state"
                    value="${customer.address.state}"
                    required="true">
                    <atlasFormTagLib:optionList
                        from="${States.values()}"
                        noSelectionLabel="Selecione o estado"/>
                </atlas-select>

                <atlas-input
                    label="Bairro"
                    placeholder="Ex: Bom Retiro"
                    name="district"
                    value="${customer.address.district}"
                    required="true">
                </atlas-input>

                <atlas-input
                    label="Rua"
                    placeholder="Ex: Rua João da Silva"
                    name="street"
                    value="${customer.address.street}"
                    required="true">
                </atlas-input>

                <atlas-integer-input
                    label="Número"
                    placeholder="000"
                    name="number"
                    value="${customer.address.number}"
                    required="true">
                </atlas-integer-input>

                <atlas-input
                    label="Complemento"
                    placeholder="Ex: Apartamento 00, Bloco 00"
                    name="complement"
                    value="${customer.address.complement}">
                </atlas-input>
            </atlas-panel>  
        </atlas-layout>
    </atlas-form-panel>
</body>
</html>
