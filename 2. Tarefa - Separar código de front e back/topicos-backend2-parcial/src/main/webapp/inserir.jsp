<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Inserir contato</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>

<div class="container">
    <div class="row">
        <div class="offset-lg-3 col-lg-6">
            <h1>Inserir contato</h1>
            <form action="realizarInsercao.jsp" method="post">
                <label for="nome">Nome:</label>
                <input type="text" name="nome" id="nome" class="form-control" />
                <label for="telefone">Telefone:</label>
                <input type="text" name="telefone" id="telefone" class="form-control" />
                <label for="email">E-mail:</label>
                <input type="text" name="email" id="email" class="form-control" />
                <button class="btn btn-primary">Cadastrar</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>