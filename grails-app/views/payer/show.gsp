<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
</head>

<body>

<form action="${createLink(controller: "payer", action: "save")}" method="post">
    <input type="text" placeholder="Insira seu Email" name="email" value="${payer.email}">
    <input type="text" placeholder="Insira seu Cpf/Cnpj" name="cpfCnpj" value="${payer.cpfCnpj}">
    <input type="text" placeholder="Insira seu Nome" name="name" value="${payer.name}">
    <input type="text" placeholder="Insira seu endereco" name="address" value="${payer.address}">
    <input type="password" placeholder="Insira uma Senha" name="password" value="${payer.password}">
    <input type="submit" value="Cadastrar">
</form>
</body>
</html>