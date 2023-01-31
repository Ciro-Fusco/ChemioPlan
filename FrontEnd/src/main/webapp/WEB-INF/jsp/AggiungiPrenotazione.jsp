<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Aggiungi Prenotazione</title>

    <link rel="stylesheet" href="/css/style.css">
    <script src="/js/script.js"></script>

</head>

<body>
    <!-- Header -->
    <%@include file="/Content/header.jsp" %>

    <!-- Content -->
    <h2>${message}</h2>

    <h1>Aggiungi Prenotazione</h1>

    <%--@elvariable id="prenotazione" type="com.example.FrontEnd.FrontEnd.model.Prenotazione"--%>
    <form:form action="/prenotazioni/add-prenotazione" method="post" modelAttribute="prenotazione">
        <div class="page_content">
            <div class="filter_content">
                <div class="filter_screen">
                    <h4>Codice Fiscale</h4>
                    <form:input class="inp_filter" path="codiceFiscale" placeholder="CodiceFiscale"/>
                </div>

                <div class="filter_screen">
                    <h4>Data</h4>
                    <form:input type="datetime-local" class="inp_filter" path="data" placeholder="Data"/>
                </div>

                <div class="filter_screen">
                    <h4>Sala</h4>
                    <form:input class="inp_filter" path="sala" placeholder="Sala"/>
                </div>

                <div class="filter_screen">
                    <h4>Poltrona</h4>
                    <form:input class="inp_filter" path="poltrona" placeholder="Poltrona"/>
                </div>

                <div class="filter_screen">
                    <h4>Codici Farmaci</h4>
                    <form:input class="inp_filter" path="codiceFarmaci" placeholder="CodiceFarmaci"/>
                </div>

            </div>
            <form:button class="button button_fill menu" href="">Aggiungi</form:button>
        </div>
    </form:form>

    <!-- Footer -->
    <%@include file="/Content/footer.jsp" %>

</body>

</html>