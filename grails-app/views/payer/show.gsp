<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
</head>

<body>

<form>

    <fieldset>
            <legend>${payer.name}</legend>
                        
        <fieldset>
            <legend>Dados do Pagador</legend>
            
            <div>
                <label for="name">Vinculado ao Cliente</label><br>
                <input type="text" value="${payer.customer.name}" disabled="true"><br>
            </div><br>
            
            <div>
                <label for="name">Nome</label><br>
                <input type="text" value="${payer.name}" disabled="true"><br>
            </div><br>
            
            <div>
                <label for="email">Email</label><br>
                <input type="email" value="${payer.email}" disabled="true"><br>
            </div><br>
        
            <div>
                <label for="cpfCnpj">CPF/CPNJ</label><br>
                <input type="text"  value="${payer.cpfCnpj}" disabled="true"><br>
            </div><br>
        </fieldset>
        
  
        <fieldset>
            <legend>Endereço</legend>

            <div>
                <label for="cep">CEP</label><br>
                <input type="text" value="${payer.address.cep}" disabled="true"><br>
            </div><br>
            
            <div>
                <label for="state">Estado</label><br>
                <input type="text" value="${payer.address.state}" maxlength="2" disabled="true"><br>
            </div><br>
                        
            <div>
                <label for="city">Cidade</label><br>
                <input type="text" value="${payer.address.city}" disabled="true"><br>
            </div><br>
            
            <div>
                <label for="district">Bairro</label><br>
                <input type="text" value="${payer.address.district}" disabled="true"><br>
            </div><br>
            
            <div>
                <label for="street">Logradouro</label><br>
                <input type="text" value="${payer.address.street}" disabled="true"><br>
            </div><br>
            
            <div>
                <label for="number">Número</label><br>
                <input type="text" value="${payer.address.number}" disabled="true"><br>
            </div><br>

            <div>
                <label for="complement">Complemento</label><br>
                <input type="text" value="${payer.address.complement}" disabled="true"><br>
            </div><br>
            
        </fieldset>

        <a href="${createLink(controller: 'payer', action: 'edit', id: payer.id)}">Editar Pagador</a>
    </fieldset>
</form>
</body>
</html>