<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="br.com.fatecmogidascruzes.topicosbackend1.Contato" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Confirmar exclusão</title>
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
            <h1>Confirmar exclusão</h1>
            <%
                Contato contato = (Contato) request.getAttribute("contato");
                if(null == contato) {
                    request.setAttribute("operacao", "/confirmarExclusao.jsp");
                    request.getRequestDispatcher("buscarContato").forward(request, response);
                    return;
                }

            %>
            <p>Você realmente deseja excluir o contato <%= contato.getNome() %>?</p>
            <a href="realizarExclusao.jsp?id=<%= contato.getId() %>" class="btn btn-primary">Sim</a>
            <a href="index.jsp" class="btn btn-warning">Desisti!</a>
            <%
                String erro = (String) request.getAttribute("erro");
                if(null != erro){
                    out.println(erro);
                }
            %>
        </div>
    </div>
</div>
</body>
</html>