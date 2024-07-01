<%@ page 
import="com.mini.asaas.enums.States"
contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="notLogged"/>
</head>
<body>
    <atlas-page >
        <atlas-page-header page-name="Cadastro" slot="header"></atlas-page-header>
        <atlas-page-content slot="content">
            <atlas-panel header="Cliente">
                <atlas-form action="${createLink(controller: "customer", action: "save")}" method="post">
                    <atlas-panel header="Dados da Conta">       
                        <atlas-input
                            label="Nome"
                            placeholder="Ex: João da Silva"
                            name="name"
                            value="${params.name}"
                            required="true">
                        </atlas-input>

                        <atlas-input
                            label="Email"
                            placeholder="Ex: joao.silva@gmail.com"
                            name="email"
                            value="${params.email}"
                            required="true">
                        </atlas-input>

                        <atlas-masked-input
                            label="CPF/CNPJ"
                            placeholder="000.000.000-00"
                            name="cpfCnpj"
                            mask-alias="cpf-cnpj"
                            value="${params.cpfCnpj}"
                            required="true">
                        </atlas-masked-input>
                    </atlas-panel>

                    <atlas-panel header="Endereço">  
                        <atlas-integer-input
                            label="CEP"
                            placeholder="00000-000"
                            name="cep"
                            value="${params.cep}"
                            required="true">
                        </atlas-integer-input>

                        <atlas-input
                            label="Cidade"
                            placeholder="Ex: Joinville"
                            name="city"
                            value="${params.city}"
                            required="true">
                        </atlas-input>

                        <atlas-select
                            label="Estado"
                            name="state"
                            value="${params.state}"
                            required="true">
                            <atlasFormTagLib:optionList
                                from="${States.values()}"
                                noSelectionLabel="Selecione o estado"/>
                        </atlas-select>

                        <atlas-input
                            label="Bairro"
                            placeholder="Ex: Bom Retiro"
                            name="district"
                            value="${params.district}"
                            required="true">
                        </atlas-input>

                        <atlas-input
                            label="Rua"
                            placeholder="Ex: Rua João da Silva"
                            name="street"
                            value="${params.street}"
                            required="true">
                        </atlas-input>

                        <atlas-integer-input
                            label="Número"
                            placeholder="000"
                            name="number"
                            value="${params.number}"
                            required="true">
                        </atlas-integer-input>

                        <atlas-input
                            label="Complemento"
                            placeholder="Ex: Apartamento 00, Bloco 00"
                            name="complement"
                            value="${params.complement}">
                        </atlas-input>
                        
                        <atlas-button
                                submit
                                description="Salvar">
                        </atlas-button>
                    </atlas-panel>
                </atlas-form>
            </atlas-panel>
        </atlas-page-content>
    </atlas-page>
</body>
</html>
