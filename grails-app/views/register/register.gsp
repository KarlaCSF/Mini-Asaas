<!DOCTYPE html>
<html>
<head>
    <title>Registrar</title>
</head>
<body>
    <h1>Registrar</h1>
    <g:form action="register" method="post">
        <div>
            <label for="email">Email:</label>
            <g:textField name="email" required="" />
        </div>
        <div>
            <label for="password">Senha:</label>
            <g:passwordField name="password" required="" />
        </div>
        <div>
            <g:submitButton name="register" value="Registrar" />
        </div>
    </g:form>
</body>
</html>
