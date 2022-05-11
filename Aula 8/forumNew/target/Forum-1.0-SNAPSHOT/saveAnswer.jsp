<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Inserir resposta</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>

<div class="container">
    <div class="row">
        <div class="offset-lg-3 col-lg-6">
            <h2>Inserir resposta</h2>
            <form action="answer" method="post">
                <label for="description">Descrição:</label>
                <input type="text" name="description" id="description" class="form-control"/>

                <button class="btn btn-primary" name="action" value="save">Cadastrar</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>