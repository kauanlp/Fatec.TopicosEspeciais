<!DOCTYPE html>
<html>
    <head>
        <title>Exemplo: requisição</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
              type="text/css" />    
    </head>
    <body>
        <div class="container">
            <p><strong>Preencha o formulário seguinte.</strong></p>
            <form action="${pageContext.request.contextPath}/do/pergunta/nova">
                <div class="col-12">
                    <label for="titulo">Título</label>
                    <input type="text" name="pergunta.titulo" id="titulo" class="form-control" />
                </div>
                <div class="col-12">
                    <label for="descricao">Descrição:</label>
                    <input type="text" name="pergunta.descricao" id="descricao" class="form-control" />
                </div>
                <div class="col-12">
                    <label for="outroDado1">Outro dado 1 (inteiro):</label>
                    <input type="number" name="outroDado1" id="outroDado1" class="form-control"  />
                </div>
                <div class="col-12">
                    <label for="outroDado2">Outro dado 2 (real):</label>
                    <input type="text" name="outroDado2" id="outroDado2" class="form-control"  />
                </div>
                <button class="btn btn-primary">Enviar</button>
            </form>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>
    </body>
</html>
