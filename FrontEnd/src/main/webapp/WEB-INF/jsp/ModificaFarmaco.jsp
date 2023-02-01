<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>

    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
            <!DOCTYPE html>
            <html lang="en">

            <head>

                <meta charset="UTF-8">
                <meta http-equiv="X-UA-Compatible" content="IE=edge">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Aggiungi Farmaco</title>

                <link rel="stylesheet" href="/css/style.css">
                <script src="/js/script.js"></script>

            </head>

            <body>
                <!-- Header -->
                <%@include file="/Content/header.jsp" %>

                    <!-- Content -->
                    <h2>${message}</h2>

                    <h1>Modifica Farmaco ${codice}</h1>

                    <%--@elvariable id="scheda" type="com.example.FrontEnd.FrontEnd.model.SchedaFarmaco" --%>
                        <form:form action="/farmacia/modifica-farmaco/${codice}" method="post" modelAttribute="scheda">
                            <div class="page_content">
                                <div class="filter_content">

                                    <!-- <div class="filter_screen">
                    <h4>Codice Farmaco</h4>
                    <form:input class="inp_filter" path="codice" placeholder="Codice" readonly="true"/>
                </div> -->
                                    <form:hidden path="codice" />

                                    <div class="filter_screen">
                                        <h4>Nome Farmaco</h4>
                                        <form:input class="inp_filter" path="nome" placeholder="Nome" />
                                        <form:errors path="nome" cssClass="error" />
                                    </div>

                                    <div class="filter_screen">
                                        <h4>Dosaggio</h4>
                                        <form:input class="inp_filter" path="dosaggio" placeholder="Dosaggio"
                                            type="number" min="1" />

                                        <form:errors path="dosaggio" cssClass="error" />
                                    </div>

                                    <div class="filter_screen">
                                        <h4>Durata dopo apertura<br>(in giorni)</h4>
                                        <form:input class="inp_filter" path="scadenzaDopoApertura" placeholder="Durata"
                                            type="number" min="1" />
                                        <form:errors path="scadenzaDopoApertura" cssClass="error" />
                                    </div>

                                </div>
                                <form:button class="button button_fill menu" href="">Modifica</form:button>
                            </div>
                        </form:form>

                        <!-- Footer -->
                        <%@include file="/Content/footer.jsp" %>

            </body>

            </html>