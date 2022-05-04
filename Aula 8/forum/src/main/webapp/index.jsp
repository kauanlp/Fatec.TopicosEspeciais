<%@ page import="java.util.List" %>
<%@ page import="com.topicos.forum.Pergunta" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<%
    List<Pergunta> perguntas = (List<Pergunta>) request.getAttribute("perguntas");

    for (Pergunta pergunta : perguntas) {
%>
<h1>
    <%= pergunta.getDescricao()%>
</h1>
<%
    }
%>
<br/>
</body>
</html>