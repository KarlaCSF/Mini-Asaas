<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
</head>

<body>
<form action="${createLink(controller: "payer", action: "save")}">
    <label for="email">Email</label>
    <input type="text" placeholder="Insira seu Email" name="email"  id="email">

    <label for="cpfCnpj">CPF/CPNJ</label>
    <input type="text" placeholder="Insira seu Cpf/Cnpj" name="cpfCnpj" id="cpfCnpj">

    <label for="name">Nome</label>
    <input type="text" placeholder="Insira seu Nome" name="name" id="name">

    <label for="address">Endereço</label>
    <input type="text" placeholder="Insira seu endereco" name="address" id="address">

    <label for="password">Senha</label>
    <input type="password" placeholder="Insira uma Senha" name="password" id="password">

    <input type="submit" value="Cadastrar">
</form>
</body>
</html>