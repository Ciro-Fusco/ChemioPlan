<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
            <!DOCTYPE html>
            <html lang="en">

            <head>

                <meta charset="UTF-8">
                <meta http-equiv="X-UA-Compatible" content="IE=edge">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Aggiungi Prenotazione</title>

                <link rel="stylesheet" href="/css/style.css">
                <script src="/js/script.js"></script>
                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>

            </head>
            <c:choose>
                <c:when test="${ruolo != 'Dottore'}">
                    <jsp:forward page="ErrorLogged.jsp" />
                </c:when>
            </c:choose>

            <body>
                <!-- Header -->
                <%@include file="/Content/header.jsp" %>

                    <!-- Content -->
                    <h2>${message}</h2>

                    <h1>Aggiungi Prenotazione</h1>

                <%--@elvariable id="prenotazione" type="com.example.frontend.model.Prenotazione" --%>
                        <form:form action="/prenotazioni/add-prenotazione" method="post" modelAttribute="prenotazione">
                            <div class="page_content">
                                <div class="filter_content">

                                    <div class="filter_screen">
                                        <h4>Codice Fiscale</h4>
                                        <form:input class="inp_filter" path="codiceFiscale"
                                            placeholder="CodiceFiscale" />
                                        <form:errors path="codiceFiscale" cssClass="error" />
                                        <p class="error">${msg_paziente}</p>
                                    </div>

                                    <div class="filter_screen">
                                        <h4>Data</h4>
                                        <form:input type="datetime-local" class="inp_filter" path="data"
                                            placeholder="Data" />
                                        <form:errors path="data" cssClass="error" />
                                        <div id="consiglio" class="info"></div>
                                    </div>

                                    <div class="filter_screen">
                                        <h4>Sala</h4>
                                        <form:input class="inp_filter" path="sala" placeholder="Sala" />
                                        <form:errors path="sala" cssClass="error" />
                                    </div>

                                    <div class="filter_screen">
                                        <h4>Poltrona</h4>
                                        <form:input class="inp_filter" path="poltrona" placeholder="Poltrona" />
                                        <form:errors path="poltrona" cssClass="error" />
                                    </div>
                                </div>
                                <form:button class="button button_fill menu" href="">Aggiungi</form:button>
                            </div>
                        </form:form>

                        <!-- Footer -->
                        <%@include file="/Content/footer.jsp" %>

                            <script>

                                $(document).ready(function () {
                                    $("#codiceFiscale").keyup(function () {
                                        var cf = $("#codiceFiscale").val();
                                        $.ajax({
                                            type: "GET",
                                            url: "http://localhost:3000/mlservices/scheduling",
                                            data: {
                                                cf: cf
                                            },
                                            success: function (response) {
                                                console.log("Risultato: " + response);
                                                $("#consiglio").html(response);
                                            }
                                        });
                                    });
                                });

                            </script>


            </body>

            </html>