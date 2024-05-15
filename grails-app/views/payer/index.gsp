<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
</head>

<body>
<form action="${createLink(controller: "payer", action: "save")}" method="POST">

    <fieldset>
        <g:if test="${params.errorMessage}">
                <span>${params.errorMessage}</span>
        </g:if>
        
        <div>
            <label for="email">Email</label>
            <input type="email" placeholder="Insira seu Email" name="email" value="${params.email}" id="email" required>
        </div>
    
        <div>
            <label for="cpfCnpj">CPF/CPNJ</label>
            <input type="text" placeholder="Insira seu Cpf/Cnpj" name="cpfCnpj" value="${params.cpfCnpj}" id="cpfCnpj" required>
        </div>
        
        <div>
            <label for="name">Nome</label>
            <input type="text" placeholder="Insira seu Nome" name="name" value="${params.name}" id="name" required>
        </div>
  
    </fieldset>

    <input type="submit" value="Cadastrar">
</form>
</body>
</html>