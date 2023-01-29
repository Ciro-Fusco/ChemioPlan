<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Aggiungi Paziente</title>

        <link rel="stylesheet" href="/css/style.css">
        <script src="/js/script.js"></script>

    </head>

    <body>
    <!-- Header -->
    <%@include file="/Content/header.jsp" %>

    <!-- Content -->
    <h2>${message}</h2>

    <h1>Modifica Trattamento</h1>

    <%--@elvariable id="scheda" type="com.example.FrontEnd.FrontEnd.model.SchedaPaziente"--%>
    <form:form action="/pazienti/modifica-paziente" method="post" modelAttribute="scheda">
        <div class="page_content">
            <div class="filter_content">
                <div class="filter_screen">
                    <h4>Codice Fiscale</h4>
                    <form:input class="inp_filter" path="codiceFiscale" readonly="true"/>
                </div>

                <div class="filter_screen" id="farmaci">
                    <h4>Codici Farmaci</h4>
                    <c:forEach items="${farmaci}" var = "f">
                    <!-- <div class="filter_content"> -->
                    <div class="checkcontainer">
                                <form:checkbox path="codiceFarmaci" value="${f.codice}"
                                    class="checkbox"></form:checkbox>
                                    <label class="checktext">${f.nome}</label>
                    </div>
                    </c:forEach>
                </div>


                <div class="filter_screen">
                    <h4>Malattie</h4>
                    <form:input class="inp_filter" path="malattie" placeholder="Malattie"/>
                </div>
            </div>
            <form:button class="button button_fill menu" href="">Modifica</form:button>
        </div>
    </form:form>

    <!-- Footer -->
    <%@include file="/Content/footer.jsp" %>

    </body>

</html>