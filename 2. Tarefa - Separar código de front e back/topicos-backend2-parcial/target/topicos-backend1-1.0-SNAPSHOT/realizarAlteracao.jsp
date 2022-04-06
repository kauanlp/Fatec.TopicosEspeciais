<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="br.com.fatecmogidascruzes.topicosbackend1.Contato" %>
<!DOCTYPE html>
<html>
<head>
    <title>Confirmar alteração</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>

<div class="container">
    <div class="row">
        <div class="offset-lg-3 col-lg-6">
            <%
                String contatoAlteradoComSucesso = (String) request.getAttribute("contatoAlteradoComSucesso");
                if(null == contatoAlteradoComSucesso) {
                    request.getRequestDispatcher("alterarContato").forward(request, response);
                }
            %>
            <h1>O contato foi alterado com sucesso!</h1>
            <a href="index.jsp" class="btn btn-primary">Voltar para a listagem</a>
        </div>
    </div>
</div>
</body>
</html>