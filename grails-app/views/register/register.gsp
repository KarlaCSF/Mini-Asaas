<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Registrar</title>
</head>
<body>
    <atlas-page>
        <atlas-page-content slot="content">
                <atlas-layout alignment="center" gap="4">
                    <atlas-panel header="Crie sua conta" style="max-width: 600px;">
                        <atlas-form action="register" method="post">
                            
                            <atlas-input
                                label="Email"
                                placeholder="Ex: joao.silva@gmail.com"
                                name="email"
                                value="${params.email}"
                                required="true">
                            </atlas-input>

                            <atlas-password-input   
                                label="Senha"
                                name="password"
                                value="${params.password}"
                                required="true">
                            </atlas-password-input>

                            <atlas-button
                                submit
                                description="Salvar">
                            </atlas-button>
                        </atlas-form>
                    </atlas-panel>
                </atlas-layout>
        </atlas-page-content>
    </atlas-page>
</body>
</html>
