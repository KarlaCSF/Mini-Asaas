<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
</head>
<body>
    <div>
        <h2>Usuários</h2>
        <g:each var="user" in="${userList}">
            <span>Email: ${user.username}</span><br>
        </g:each> 
            
        <g:form action="addUser" method="post">
            <h3>Adicionar Usuário</h3>

            <label for="email">Email:</label>
            <g:textField name="email" required="true"/><br>
            
            <label for="role">Função:</label>
            <g:select name="role" from="${roleList.authority}" required="true"/><br><br>
    
            <g:submitButton name="add" value="Adicionar" />
        </g:form>        
    </div>
</body>
</html>
