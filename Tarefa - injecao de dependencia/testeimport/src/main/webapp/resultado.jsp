<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Resultado</title>
    </head>
    <body>
        <h1>Resultado</h1>
        <p>Você digitou os seguintes dados no formulário:</p>
        <ul>
            <li>${resposta.tituloPergunta}</li>
            <li>${resposta.descricaoPergunta}</li>
            <li>${resposta.outroDado1}</li>
            <li>${resposta.outroDado2}</li>
        </ul>
    </body>
</html>
