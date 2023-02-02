<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
            <!DOCTYPE html>
            <html lang="en">

            <head>

                <meta charset="UTF-8">
                <meta http-equiv="X-UA-Compatible" content="IE=edge">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Cerca Paziente</title>

                <link rel="stylesheet" href="/css/style.css">
                <script src="/js/script.js"></script>

            </head>

            <body>
                <!-- Header -->
                <%@include file="/Content/header.jsp" %>

                    <!-- Content -->
                    <h2>${message}</h2>

                    <h1>Cerca Paziente</h1>

                    <form:form action="/pazienti/ricerca-paziente" method="post" modelAttribute="paziente">
                        <div class="page_content">
                            <div class="filter_content">
                                <div class="filter_screen">
                                    <h4>Codice fiscale</h4>
                                    <form:input class="inp_filter" path="codiceFiscale" placeholder="Codice fiscale" />
                                    <form:errors path="codiceFiscale" cssClass="error" />

                                </div>

                                <div class="filter_screen">
                                    <h4>Data di nascita</h4>
                                    <form:input type="date" class="inp_filter" path="dataNascita"
                                        placeholder="Data di nascita" />
                                    <form:errors path="dataNascita" cssClass="error" />
                                </div>


                                <div class="filter_screen">
                                    <h4>Nome</h4>
                                    <form:input class="inp_filter" path="nome" placeholder="nome" />
                                    <p class="error">${nome_error}</p>
                                </div>


                                <div class="filter_screen">
                                    <h4>Cognome</h4>
                                    <form:input class="inp_filter" path="cognome" placeholder="cognome" />
                                    <p class="error">${cognome_error}</p>
                                </div>

                                <div class="filter_screen">
                                    <h4>Luogo di nascita</h4>
                                    <form:input class="inp_filter" path="luogoNascita" placeholder="luogo di nascita" />
                                </div>
                            </div>
                            <form:button class="button button_fill menu" href="">Cerca</form:button>
                        </div>
                    </form:form>

                    <!-- Footer -->
                    <%@include file="/Content/footer.jsp" %>

            </body>

            </html>