<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Editar</title>
</head>
<body>
    <g:if test="${flash.message}">
        <atlas-alert message="${flash.message}" type="${flash.type}"></atlas-alert>
    </g:if>
    
    <atlas-page>
        <atlas-page-content slot="content">
        
                <atlas-layout alignment="center" gap="4">
                    <atlas-panel header="Alterar senha" style="max-width: 600px;">
                        <atlas-form action="update" method="post">
                            
                            <atlas-input
                                label="Email"
                                placeholder="Ex: joao.silva@gmail.com"
                                name="email"
                                value="${user.username}"
                                required="true"
                                disabled="true">
                            </atlas-input>

                            <atlas-password-input   
                                label="Senha"
                                name="password">
                            </atlas-password-input>

                            <atlas-button
                                submit
                                required="true"
                                description="Salvar">
                            </atlas-button>
                        </atlas-form>
                    </atlas-panel>
                </atlas-layout>
        </atlas-page-content>
    </atlas-page>
</body>
</html>
