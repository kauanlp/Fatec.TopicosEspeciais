<%@ page import="java.util.List" %>
<%@ page import="br.com.fatecmogidascruzes.avaliacao.dominio.Produto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Listagem de produtos</title>
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
            <h1>Produtos existentes</h1>
            <a href="inserir.jsp" class="btn btn-primary">Cadastrar novo</a>
            <table class="table">
                <thead>
                <tr>
                    <th class="col">Foto</th>
                    <th class="col">Nome</th>
                    <th class="col">Preço Unitário (R$)</th>
                    <th class="col">Ações</th>
                </tr>
                </thead>
                <tbody>
                <%
                    List<Produto> produtos = (List<Produto>) request.getAttribute("produtos");
                    if(null == produtos) {
                        request.getRequestDispatcher("/produto").forward(request, response);
                        return;
                    }

                    for(Produto produto : produtos) {
                %>
                <tr>
                    <td>
                        <img src="<%= produto.getLinkFoto() %>" alt="Foto" id="linkFoto" width="100">
                    </td>
                    <td><%= produto.getNome() %>
                    </td>
                    <td><%= produto.getPrecoUnitario() %>
                    </td>
                    <td>
                        <a href="produto?acao=excluir&excluir=<%= produto.getId() %>"><span class="fa-solid fa-trash text-danger"></span></a>
                        <a href="produto?acao=alterar&alterar=<%= produto.getId() %>"><span class="fa-solid fa-pencil text-primary"></span></a>
                    </td>
                </tr>
                <%
                    }
                %>
                </tbody>
            </table>
            <p>Foram encontrados <%= produtos.size() %> produtos.</p>
        </div>
    </div>
</div>
</body>
</html>