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
            <input type="text" placeholder="Insira seu Email" name="email" value="${params.email}" id="email">
            <g:if test="${!params.email && params.errorMessage}">
                <span>* O campo email é obrigatório</span>
            </g:if>
        </div>
    
        <div>
            <label for="cpfCnpj">CPF/CPNJ</label>
            <input type="text" placeholder="Insira seu Cpf/Cnpj" name="cpfCnpj" value="${params.cpfCnpj}" id="cpfCnpj">
            <g:if test="${!params.cpfCnpj && params.errorMessage}">
                <span>* O campo CPF/CNPJ é obrigatório</span>
            </g:if>
        </div>
        
        <div>
            <label for="name">Nome</label>
            <input type="text" placeholder="Insira seu Nome" name="name" value="${params.name}" id="name">
            <g:if test="${!params.name && params.errorMessage}">
                <span>* O campo nome é obrigatório</span>
            </g:if>
        </div>
  
    </fieldset>

    <input type="submit" value="Cadastrar">
</form>
</body>
</html>