<!doctype html>
<html>
<head>
</head>
<body>
    <div>
        <h2>Usuários</h2>

        <g:each var="user" in="${userList}">
            <span>Email: ${user.username}</span><br>
        </g:each> 
            
        <g:form action="add" method="post">
            <h3>Adicionar Usuário</h3>

            <g:if test="${params.errorMessage}">
                <span>${params.errorMessage}</span>
            </g:if>

            <div>
                <label for="email">Email:</label>
                <g:textField name="email" required="true"/><br>
                
                <label for="role">Função:</label>
                <g:select name="role" from="${roleList.authority}" valueMessagePrefix="Role" required="true"/><br><br>
        
                <g:submitButton name="add" value="Adicionar" />
            </div>
        </g:form>        
    </div>
</body>
</html>
