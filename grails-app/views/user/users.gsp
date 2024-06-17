<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
</head>
<body>
    <atlas-page>
        <atlas-page-header page-name="Usuários" slot="header"></atlas-page-header>
        <atlas-page-content slot="content">
            <atlas-panel header="Usuários que tem acesso a essa conta">
                <g:each var="user" in="${userList}">
                    <ul class="atlas-simple-list" style="margin: 0; padding: 0 10px;">${user.username}</ul>
                </g:each> 
            </atlas-panel>

            <atlas-panel header="Adicionar usuário">
                <atlas-form action="add" method="post">
                    <atlas-input
                        label="Email"
                        placeholder="Ex: joao.silva@gmail.com"
                        name="email"
                        value="${params.email}"
                        required="true">
                    </atlas-input>

                    <atlas-select
                        label="Função"
                        name="role"
                        value="${params.role}"
                        required="true">
                        <atlasFormTagLib:optionList
                            from="${roleList.authority}"
                            valueMessagePrefix="Role"/>                        
                    </atlas-select>

                    <atlas-button
                        submit=""
                        description="Adicionar">
                    </atlas-button>
                </atlas-form>
            </atlas-panel>
        </atlas-page-content>
    </atlas-page>
</body>
</html>
