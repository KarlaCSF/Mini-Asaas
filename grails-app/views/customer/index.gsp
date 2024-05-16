<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
</head>
<body>
    <form action="${createLink(controller: "customer", action: "save")}">
        <label for="name">Nome:</label><br>
        <input type="text" name="name"><br>
        
        <label for="email">Email:</label><br>
        <input type="text" name="email"><br><br>

        <label for="cpfCnpj">CPF/CNPJ:</label><br>
        <input type="text" name="cpfCnpj"><br><br>

        <div>Endereço 
            <br>
            <label for="cep">CEP:</label><br>
            <input type="text" name="cep"><br><br>

            <label for="city">Cidade:</label><br>
            <input type="text" name="city"><br><br>

            <label for="state">Estado:</label><br>
            <input type="text" name="state"><br><br>

            <label for="district">Bairro:</label><br>
            <input type="text" name="district"><br><br>

            <label for="street">Rua:</label><br>
            <input type="text" name="street"><br><br>

            <label for="number">Número:</label><br>
            <input type="text" name="number"><br><br>

            <label for="complement">Complemento:</label><br>
            <input type="text" name="complement"><br><br>
        </div>

        <input type="submit" value="Submit">
    </form>
</body>
</html>