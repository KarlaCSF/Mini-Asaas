<!doctype html>
<html>
<head>
</head>
<body>
    <div>
        <h2>Perfil</h2>

        <g:if test="${params.errorMessage}">
            <span>${params.errorMessage}</span>
        </g:if>
            
        <g:form action="update" method="post">
            <label for="email">Email:</label>
            <g:textField name="email" value="${user.username}" required="true" disabled="true"/><br>
            
            <label for="password">Nova Senha:</label>
            <g:passwordField name="password" required="true"/><br>

            <g:submitButton name="update" value="Atualizar" />
        </g:form>        
    </div>
</body>
</html>
