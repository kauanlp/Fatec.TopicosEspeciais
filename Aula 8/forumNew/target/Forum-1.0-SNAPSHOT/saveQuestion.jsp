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
            <h2>Inserir pergunta</h2>
            <form action="question" method="post">
                <label for="title">Título:</label>
                <input type="text" name="title" id="title" class="form-control"/>
                <label for="description">Descrição:</label>
                <input type="text" name="description" id="description" class="form-control"/>

                <label for="tag">Escolha uma tag:</label>
                <select id="tag" name="tag">
                    <option value="Frontend">Frontend</option>
                    <option value="Backend">Backend</option>
                    <option value="Banco de Dados">Banco de Dados</option>
                </select>
                </br>

                <button class="btn btn-primary" name="action" value="save">Cadastrar</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>