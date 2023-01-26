<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
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

    <h1>Aggiungi Paziente</h1>

    <form:form action="/pazienti/add-paziente" method="post" modelAttribute="scheda">
        <div class="page_content">
            <div class="filter_content">
                <div class="filter_screen">
                    <h4>Codice Fiscale</h4>
                    <form:input class="inp_filter" path="codiceFiscale" placeholder="Codice"/>
                </div>

                <div class="filter_screen" id="farmaci">
                    <h4>Codici Farmaci</h4>
                    <c:forEach items="${farmaci}" var = "f">
                        <form:checkbox path="codiceFarmaci" value="${f.codice}" class="check"></form:checkbox>
                        <label>${f.nome}</label>
                    </c:forEach>
                </div>

                <div class="filter_screen">
                    <h4>Malattie</h4>
                    <form:input class="inp_filter" path="malattie" placeholder="Malattie"/>
                </div>
            </div>
            <form:button class="button button_fill menu" href="">Aggiungi</form:button>
        </div>
    </form:form>

    <!-- Footer -->
    <%@include file="/Content/footer.jsp" %>

    </body>

</html>