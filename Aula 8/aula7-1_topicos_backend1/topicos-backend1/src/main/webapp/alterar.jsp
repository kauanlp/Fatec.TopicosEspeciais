<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="br.com.fatecmogidascruzes.topicosbackend1.Contato" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Alterar contato</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/fontawesome.min.css"
          integrity="sha512-8Vtie9oRR62i7vkmVUISvuwOeipGv8Jd+Sur/ORKDD5JiLgTGeBSkI3ISOhc730VGvA5VVQPwKIKlmi+zMZ71w=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/solid.min.css"
          integrity="sha512-6/gTF62BJ06BajySRzTm7i8N2ZZ6StspU9uVWDdoBiuuNu5rs1a8VwiJ7skCz2BcvhpipLKfFerXkuzs+npeKA=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
</head>
<body>

<div class="container">
    <div class="row">
        <div class="offset-lg-3 col-lg-6">
            <h1>Alterar contato</h1>
            <%
                Contato contato = (Contato) request.getAttribute("contato");
                if (contato != null) {
            %>
                <form action="contato" method="get">
                    <input type="hidden" name="id" value="<%= contato.getCodigo() %>" />
                    <label for="nome">Nome:</label>
                    <input type="text" name="nome" id="nome" class="form-control" value="<%= contato.getNome() %>" />
                    <label for="telefone">Telefone:</label>
                    <input type="text" name="telefone" id="telefone" class="form-control" value="<%= contato.getTelefone() %>" />
                    <label for="email">E-mail:</label>
                    <input type="text" name="email" id="email" class="form-control" value="<%= contato.getEmail() %>" />
                    <button class="btn btn-primary" name="acao" value="atualizar">Alterar</button>
                </form>
            <%
                } else {
                    out.println("Nenhum contato com este identificador foi encontrado");
                }
            %>
        </div>
    </div>
</div>
</body>
</html>