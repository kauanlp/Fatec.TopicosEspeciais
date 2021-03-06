<%@ page import="com.forum.domain.Answer" %>
<%@ page import="java.util.List" %>
<%@ page import="com.forum.domain.Question" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <title>Forum</title>
</head>
<body>
<div class="container-fluid mt-100">
    <div class="row align-items-center h-100">
        <div class="col-md-6 mx-auto">
            <div class="mb-4 d-flex justify-content-center">
                <img src="https://img.icons8.com/external-inipagistudio-mixed-inipagistudio/64/000000/external-forum-social-media-marketing-inipagistudio-mixed-inipagistudio.png"/>
                <h2>FatecForum</h2>
            </div>
            <div class="card mb-4">
                <%
                    Question question = (Question) request.getAttribute("question");
                    if (question != null) {
                %>
                <div class="card-header">
                    <div class="flex-wrap w-100 align-items-center">
                        <div class="media">
                            <div class="media-body">
                                <%=question.getId()%> - <%=question.getTitle()%>
                            </div>
                            <div class="text-muted small ml-3">
                                <div>Publicado em <strong><%=question.getRegisterDate()%>
                                </strong></div>
                            </div>
                        </div>
                        <div class="small">
                            <div><strong><%=question.getTag()%>
                            </strong></div>
                        </div>
                    </div>
                </div>
                <div class="card-body">
                    <p><%=question.getDescription()%>
                    </p>
                </div>
                <%
                    } else {
                        out.println("Nenhuma pergunta com este identificador foi encontrada");
                    }
                %>
                <%
                    List<Answer> answers = (List<Answer>) request.getAttribute("answers");
                    if (null == answers) {
                        request.getRequestDispatcher("/answer").forward(request, response);
                        return;
                    }

                    for (Answer answer : answers) {
                %>

                <div class="card-footer d-flex flex-wrap justify-content-between align-items-center px-0 pt-0 pb-3">
                    <div class="card-body">
                        <p><%= answer.getDescription()%>
                        </p>
                    </div>
                </div>
                <%
                    }
                %>
            </div>
            <div>
                <div class="">
                    <a href="index.jsp" class="btn btn-primary">Voltar</a>
                </div>
                <div class="">
                    <a href="saveAnswer.jsp" class="btn btn-primary">Adicionar Resposta</a>
                </div>
            </div>

        </div>
    </div>
</div>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>