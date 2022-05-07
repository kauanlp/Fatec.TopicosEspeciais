<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Inserir pergunta</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>

<div class="container">
    <div class="row">
        <div class="offset-lg-3 col-lg-6">
            <h1>Inserir pergunta</h1>
            <form action="pergunta" method="post">
                <label for="titulo">Título:</label>
                <input type="text" name="titulo" id="titulo" class="form-control" />
                <label for="descricao">Descrição:</label>
                <input type="text" name="descricao" id="descricao" class="form-control" />
                <button class="btn btn-primary" name="acao" value="inserir">Cadastrar</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>