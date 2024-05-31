<%@ page 
import="com.mini.asaas.enums.States"
contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
</head>

<body>
<form action="${createLink(controller: "payer", action: "update", id: payer.id)}" method="POST">

    <fieldset>
        <legend>Editar Pagador: ${payer.name}</legend>
        
        <g:if test="${params.errorMessage}">
            <span>${params.errorMessage}</span>
        </g:if>
            
        <fieldset>
            <legend>Dados do Pagador</legend>
            
            <div>
                <label for="name">Nome</label><br>
                <input type="text" placeholder="Insira seu Nome" name="name" value="${payer.name}" id="name" required><br>
            </div><br>
            
            <div>
                <label for="email">Email</label><br>
                <input type="email" placeholder="Insira seu Email" name="email" value="${payer.email}" id="email" required><br>
            </div><br>
        
            <div>
                <label for="cpfCnpj">CPF/CPNJ</label><br>
                <input type="text" placeholder="Insira seu Cpf/Cnpj" name="cpfCnpj" value="${payer.cpfCnpj}" id="cpfCnpj" required><br>
            </div><br>

        </fieldset>
        
  
        <fieldset>
            <legend>Endereço</legend>

            <div>
                <label for="cep">CEP</label><br>
                <input type="text" placeholder="Insira o seu CEP" name="cep" value="${payer.address.cep}" id="cep" required><br>
                <a href="https://buscacepinter.correios.com.br/app/endereco/index.php">Não sei meu cep</a><br>
            </div><br>
            
            <div>
                <label for="state">Estado</label><br>
                <g:select name="state" from="${States.values()}" value="${payer.address.state}" noSelection="['':'Selecione um estado']" required="true"/><br><br>
            </div><br>
                        
            <div>
                <label for="city">Cidade</label><br>
                <input type="text" placeholder="Insira a sua Cidade" name="city" value="${payer.address.city}" id="city" required><br>
            </div><br>
            
            <div>
                <label for="district">Bairro</label><br>
                <input type="text" placeholder="Insira o seu Bairro" name="district" value="${payer.address.district}" id="district" required><br>
            </div><br>
            
            <div>
                <label for="street">Logradouro</label><br>
                <input type="text" placeholder="Insira o seu Logradouro" name="street" value="${payer.address.street}" id="street" required><br>
            </div><br>
            
            <div>
                <label for="number">Número</label><br>
                <input type="text" placeholder="Insira o seu Número" name="number" value="${payer.address.number}" id="number" required><br>
            </div><br>

            <div>
                <label for="complement">Complemento</label><br>
                <input type="text" placeholder="Insira o complemento" name="complement" value="${payer.address.complement}" id="complement"><br>
            </div><br>
            
        </fieldset>

        <input type="submit" value="Atualizar">
    </fieldset>
</form>
</body>
</html>