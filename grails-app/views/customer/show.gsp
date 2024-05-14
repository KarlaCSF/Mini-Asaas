<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
</head>
<body>
    <form action="${ createLink(controller: "customer", action: "save")}">
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
            <input type="text" name="state" value="${customer.address.state}"><br><br>

            <label for="district">Bairro:</label><br>
            <input type="text" name="district" value="${customer.address.district}"><br><br>

            <label for="street">Rua:</label><br>
            <input type="text" name="street" value="${customer.address.street}"><br><br>

            <label for="number">Número:</label><br>
            <input type="text" name="number" value="${customer.address.number}"><br><br>

            <label for="complement">Complemento:</label><br>
            <input type="text" name="complement" value="${customer.address.complement}"><br><br>
        </div>
    </form>
</body>
</html>