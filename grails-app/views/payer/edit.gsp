<%@ page
        import="com.mini.asaas.enums.States"
        contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <meta name="layout" content="main"/>
        <title>Editar pagador - Mini Asaas</title>
    </head>

    <body>
        <atlas-section header="Pagadores">
            <atlas-panel header="${payer.name}">
                <atlas-form action="${createLink(controller: "payer", action: "update")}" method="post">
                    <atlas-panel header="Dados Do Pagador">
                        <atlas-input
                                label="Nome"
                                placeholder="Insira o nome do pagador"
                                name="name"
                                value="${payer.name}"
                                required="true">
                        </atlas-input>

                        <atlas-masked-input
                                label="Email"
                                placeholder="Insira o email do pagador"
                                name="email"
                                mask-alias="email"
                                value="${payer.email}"
                                required="true">
                        </atlas-masked-input>

                        <atlas-masked-input
                                label="Cpf ou Cnpj"
                                placeholder="Insira o cpf do pagador"
                                name="cpfCnpj"
                                mask-alias="cpf-cnpj"
                                value="${payer.cpfCnpj}"
                                required="true">
                        </atlas-masked-input>
                    </atlas-panel>
                    <atlas-panel header="Endeço do Pagador">
                        <atlas-postal-code
                                label="Cep"
                                placeholder="Insira o cep do pagador"
                                name="cep"
                                value="${payer.address.cep}"
                                required="true">
                        </atlas-postal-code>
                        <a href="https://buscacepinter.correios.com.br/app/endereco/index.php"
                           target="_blank">Não sei meu cep</a>

                        <atlas-select
                                label="Estado"
                                name="state"
                                value="${payer.address.state}"
                                required="true">
                            <atlasFormTagLib:optionList
                                    from="${States.values()}"
                                    noSelectionLabel="Escolha o estado do pagador"/>
                        </atlas-select>

                        <atlas-input
                                label="Cidade"
                                placeholder="Insira a cidade do pagador"
                                name="city"
                                value="${payer.address.city}"
                                required="true">
                        </atlas-input>

                        <atlas-input
                                label="Bairro"
                                placeholder="Insira o bairro do pagador"
                                name="district"
                                value="${payer.address.district}"
                                required="true">
                        </atlas-input>

                        <atlas-input
                                label="Lougradoro"
                                placeholder="Insira o lougradoro do pagador"
                                name="street"
                                value="${payer.address.street}"
                                required="true">
                        </atlas-input>

                        <atlas-input
                                label="Número"
                                placeholder="Insira o número do pagador"
                                name="number"
                                value="${payer.address.number}"
                                required="true">
                        </atlas-input>

                        <atlas-input
                                label="Complemento"
                                placeholder="Insira o complemento do pagador"
                                name="complement"
                                value="${payer.address.complement}">
                        </atlas-input>

                    </atlas-panel>
                    <atlas-layout inline gap="2" col="2">
                        <atlas-button
                                submit
                                description="Salvar Pagador">
                        </atlas-button>
                        <atlas-button
                                href="${createLink(controller: 'payer', action: 'show', id: payer.id)}"
                                description="Cancelar"
                                theme="secondary">
                        </atlas-button>
                    </atlas-layout>
                </atlas-form>
            </atlas-panel>
        </atlas-section>
    </body>
</html>