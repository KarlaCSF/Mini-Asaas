<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
</head>

<body>

<form>
    <label for="email">Email</label>
    <input type="text" value="${payer.email}" disabled="true">

    <label for="cpfCnpj">CPF/CPNJ</label>
    <input type="text" value="${payer.cpfCnpj}" disabled="true">

    <label for="name">Nome</label>
    <input type="text" value="${payer.name}" disabled="true">
</form>
</body>
</html>