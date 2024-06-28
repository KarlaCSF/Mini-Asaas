<!doctype html>
<html lang="pt-BR" class="no-js">

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
        <title>
        <g:layoutTitle default="Mini Asaas"/>
        </title>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>

        <asset:link rel="icon" href="favicon.ico" type="image/x-ico"/>

        <asset:stylesheet src="application.css"/>

        <link rel="stylesheet" href="https://atlas.asaas.com/v15.18.0/atlas.min.css" crossorigin="anonymous">
        <script defer src="https://atlas.asaas.com/v15.18.0/atlas.min.js" crossorigin="anonymous"></script>

        <g:layoutHead/>
    </head>

    <body>
        <atlas-screen>
            <g:render template="/templates/externalHeader"/>
            <atlas-page>
                <atlas-page-content slot="content" class="js-atlas-content">
                    <g:if test="${flash.message}">
                        <atlas-alert message="${flash.message}" type="${flash.type}"></atlas-alert>
                    </g:if>
                    
                    <g:layoutBody/>
                </atlas-page-content>
            </atlas-page>
        </atlas-screen>

        <asset:javascript src="application.js"/>
    </body>
</html>
