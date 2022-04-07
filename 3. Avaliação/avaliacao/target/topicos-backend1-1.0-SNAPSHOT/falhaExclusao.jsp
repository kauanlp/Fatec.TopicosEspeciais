<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Confirmar exclusão</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>

<div class="container">
    <div class="row">
        <div class="offset-lg-3 col-lg-6">
            <h1>Houve um problema na <exclusão></exclusão></h1>
            <p><%= request.getAttribute("mensagem") %></p>
            <a href="index.jsp" class="btn btn-primary">Voltar para a listagem</a>
        </div>
    </div>
</div>
</body>
</html>