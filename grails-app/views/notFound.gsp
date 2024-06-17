<!doctype html>
<html>
    <head>
        <title>Página não encontrada - Mini Asaas</title>
        <meta name="layout" content="main">
        <g:if env="development"><asset:stylesheet src="errors.css"/></g:if>
    </head>

    <body>
        <atlas-empty-state
                illustration="error-404-clouds-birds">
            <atlas-layout
                    gap="4">
                <ul>
                    <li>Erro: Página não encontrada (404)</li>
                    <li>Caminho: ${request.forwardURI}</li>
                </ul>
                <atlas-button
                        description="Voltar para a página inicial"
                        href="${createLink(uri: "/")}">
                </atlas-button>
            </atlas-layout>
        </atlas-empty-state>
    </body>
</html>
