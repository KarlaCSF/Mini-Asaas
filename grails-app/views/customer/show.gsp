<%@ page import="com.mini.asaas.utils.CpfCnpjUtils" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
</head>
<body>
    <form action="${createLink(controller: "customer", action: "show")}">
        <label for="name">Name:</label><br>
        <input type="text" name="name" value="${customer.name}" disabled><br>
        
        <label for="email">Email:</label><br>
        <input type="text" name="email" value="${customer.email}" disabled><br><br>

        <label for="cpfCnpj">CPF/CNPJ:</label><br>
        <input type="text" name="cpfCnpj" value="${CpfCnpjUtils.applyMask(customer.cpfCnpj)}" disabled><br><br>

        <label for="tipo">Tipo de pessoa:</label><br>
        <input type="text" name="tipo" value="${customer.personType.getLabel()}" disabled><br><br>

        <div>Endereço
            <label for="cep">CEP:</label><br>
            <input type="text" name="cep" value="${customer.address.cep}" disabled><br><br>

            <label for="city">Cidade:</label><br>
            <input type="text" name="city" value="${customer.address.city}" disabled><br><br>

            <label for="state">Estado:</label><br>
            <input type="text" name="state" value="${customer.address.state}" disabled><br><br>

            <label for="district">Bairro:</label><br>
            <input type="text" name="district" value="${customer.address.district}" disabled><br><br>

            <label for="street">Rua:</label><br>
            <input type="text" name="street" value="${customer.address.street}" disabled><br><br>

            <label for="number">Número:</label><br>
            <input type="text" name="number" value="${customer.address.number}" disabled><br><br>

            <label for="complement">Complemento:</label><br>
            <input type="text" name="complement" value="${customer.address.complement}" disabled><br><br>

        </div>
    </form>
    <a href="${createLink(controller: 'customer', action: 'edit')}">Editar Cliente</a>
</body>
</html>
