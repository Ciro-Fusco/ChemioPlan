<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
            <!DOCTYPE html>
            <html lang="en">

            <head>

                <meta charset="UTF-8">
                <meta http-equiv="X-UA-Compatible" content="IE=edge">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Nuovo Ordine</title>

                <link rel="stylesheet" href="/css/style.css">
                <script src="/js/script.js"></script>

            </head>

            <body>
                <!-- Header -->
                <%@include file="/Content/header.jsp" %>

                    <!-- Content -->
                    <h2>${message}</h2>
                    <h1>Nuovo Ordine</h1>
                    <div class="wrapper">
                        <%--@elvariable id="ordine" type="com.example.FrontEnd.FrontEnd.model.OrdineRequest" --%>
                            <form:form action="/farmacia/nuovo-ordine" method="post" modelAttribute="ordine">
                                <div class="page_content">
                                    <div class="filter_content">
                                        <div class="filter_screen">
                                            <h4>Codice Farmaco</h4>
                                            <form:input class="inp_filter" path="codiceFarmaco" placeholder="Codice"
                                                maxlength="10" value="${ordine.codiceFarmaco}" />
                                            <form:errors path="codiceFarmaco" cssClass="error" />
                                        </div>

                                        <div class="filter_screen">
                                            <h4>Quantita</h4>
                                            <form:input class="inp_filter" path="quantita" placeholder="Quantita"
                                                type="number" min="1" value="${ordine.quantita}" />
                                            <form:errors path="quantita" cssClass="error" />
                                        </div>


                                    </div>

                                    <p class="error">${msg}</p>
                                    <form:button class="button button_fill menu" href="">Ordina</form:button>
                                </div>
                            </form:form>
                    </div>
                    <!-- Footer -->
                    <%@include file="/Content/footer.jsp" %>

            </body>

            </html>