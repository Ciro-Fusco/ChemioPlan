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
            <c:choose>
                <c:when test="${ruolo != 'Responsabile Farmacia'}">
                    <jsp:forward page = "ErrorLogged.jsp" />
                </c:when>
            </c:choose>

            <body>
                <!-- Header -->
                <%@include file="/Content/header.jsp" %>

                    <!-- Content -->
                    <h2>${message}</h2>

                    <h1>Modifica del Lotto ${lotto.numeroLotto} del farmaco ${codice}
                    </h1>


                    <%--@elvariable id="lotto" type="com.example.FrontEnd.FrontEnd.model.Lotto" --%>
                        <form:form action="/farmacia/modifica-lotto/${codice}" method="post" modelAttribute="lotto">

                            <div class="page_content">
                                <div class="filter_content">
                                    <!-- <div class="filter_screen">
                                        <h4>Numero Lotto</h4>
                                        <form:input class="inp_filter" path="numeroLotto" placeholder="Numero Lotto"
                                            type="number" min="1" />
                                        <form:errors path="numeroLotto" cssClass="error" />
                                    </div> -->
                                    <form:hidden path="numeroLotto" />

                                    <div class="filter_screen">
                                        <h4>Data Scadenza</h4>
                                        <form:input type="date" class="inp_filter" path="scadenzaLotto"
                                            id="datePickerId" placeholder="Data Scadenza" />
                                        <form:errors path="scadenzaLotto" cssClass="error" />
                                    </div>

                                    <div class="filter_screen">
                                        <h4>Quantita</h4>
                                        <form:input class="inp_filter" path="quantita" placeholder="Quantita"
                                            type="number" min="1" />
                                        <form:errors path="quantita" cssClass="error" />
                                    </div>
                                </div>

                                <p class="error">${msg}</p>
                                <form:button class="button button_fill menu" href="">Modifica</form:button>
                            </div>
                        </form:form>

                        <!-- Footer -->
                        <%@include file="/Content/footer.jsp" %>

            </body>

            </html>