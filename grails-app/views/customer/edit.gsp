<%@ page 
import="com.mini.asaas.enums.States"
contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
</head>
<body>
    <g:if test="${params.errorMessage}">
        <span>${params.errorMessage}</span>
    </g:if>

    <form action="${createLink(controller: "customer", action: "update")}" method="POST">
        <label for="name">Name:</label><br>
        <input type="text" name="name" value="${customer.name}"><br>
        
        <label for="email">Email:</label><br>
        <input type="text" name="email" value="${customer.email}"><br><br>

        <label for="cpfCnpj">CPF/CNPJ:</label><br>
        <input type="text" name="cpfCnpj" value="${customer.cpfCnpj}"><br><br>

        <label for="tipo">Tipo de pessoa:</label><br>
        <input type="text" name="tipo" value="${customer.personType.getLabel()}"><br><br>

        <div>Endereço
            <label for="cep">CEP:</label><br>
            <input type="text" name="cep" value="${customer.address.cep}"><br><br>

            <label for="city">Cidade:</label><br>
            <input type="text" name="city" value="${customer.address.city}"><br><br>

            <label for="state">Estado:</label><br>
            <g:select name="state" from="${States.values()}" value="${customer.address.state}" noSelection="['':'Selecione um estado']" required="true"/><br><br>

            <label for="district">Bairro:</label><br>
            <input type="text" name="district" value="${customer.address.district}"><br><br>

            <label for="street">Rua:</label><br>
            <input type="text" name="street" value="${customer.address.street}"><br><br>

            <label for="number">Número:</label><br>
            <input type="text" name="number" value="${customer.address.number}"><br><br>

            <label for="complement">Complemento:</label><br>
            <input type="text" name="complement" value="${customer.address.complement}"><br><br>
        </div>

        <input type="submit" value="Atualizar">
    </form>
    <a href="${createLink(controller: 'user', action: 'users')}">Usuários</a>
</body>
</html>
