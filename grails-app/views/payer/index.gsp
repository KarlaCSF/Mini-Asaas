<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
</head>

<body>
<form action="${createLink(controller: "payer", action: "save")}" method="POST">

    <fieldset>
        <legend>Cadastro</legend>
        
        <g:if test="${params.errorMessage}">
            <span>${params.errorMessage}</span>
        </g:if>
            
        <fieldset>
            <legend>Dados do Pagador</legend>
            
            <div>
                <label for="name">Nome</label><br>
                <input type="text" placeholder="Insira seu Nome" name="name" value="${params.name}" id="name" required><br>
            </div><br>
            
            <div>
                <label for="email">Email</label><br>
                <input type="email" placeholder="Insira seu Email" name="email" value="${params.email}" id="email" required><br>
            </div><br>
        
            <div>
                <label for="cpfCnpj">CPF/CPNJ</label><br>
                <input type="text" placeholder="Insira seu Cpf/Cnpj" name="cpfCnpj" value="${params.cpfCnpj}" id="cpfCnpj" required><br>
            </div><br>

        </fieldset>
        
  
        <fieldset>
            <legend>Endereço</legend>

            <div>
                <label for="cep">CEP</label><br>
                <input type="text" placeholder="Insira o seu CEP" name="cep" value="${params.cep}" id="cep" required><br>
                <a href="https://buscacepinter.correios.com.br/app/endereco/index.php">Não sei meu cep</a><br>
            </div><br>
            
            <div>
                <label for="state">Estado</label><br>
                <input type="text" placeholder="Insira o seu Estado" name="state" value="${params.state}" id="state" maxlength="2" required><br>
            </div><br>
                        
            <div>
                <label for="city">Cidade</label><br>
                <input type="text" placeholder="Insira a sua Cidade" name="city" value="${params.city}" id="city" required><br>
            </div><br>
            
            <div>
                <label for="district">Bairro</label><br>
                <input type="text" placeholder="Insira o seu Bairro" name="district" value="${params.district}" id="district" required><br>
            </div><br>
            
            <div>
                <label for="street">Logradouro</label><br>
                <input type="text" placeholder="Insira o seu Logradouro" name="street" value="${params.street}" id="street" required><br>
            </div><br>
            
            <div>
                <label for="number">Número</label><br>
                <input type="text" placeholder="Insira o seu Número" name="number" value="${params.number}" id="number" required><br>
            </div><br>

            <div>
                <label for="complement">Complemento</label><br>
                <input type="text" placeholder="Insira o complemento" name="complement" value="${params.complement}" id="complement"><br>
            </div><br>
            
        </fieldset>

        <input type="submit" value="Cadastrar">
    </fieldset>
</form>
</body>
</html>