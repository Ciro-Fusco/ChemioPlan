<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
            <!DOCTYPE html>
            <html lang="en">

            <head>

                <meta charset="UTF-8">
                <meta http-equiv="X-UA-Compatible" content="IE=edge">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Modifica Prenotazione</title>

                <link rel="stylesheet" href="/css/style.css">
                <script src="/js/script.js"></script>

            </head>

            <body>
                <!-- Header -->
                <%@include file="/Content/header.jsp" %>

                    <!-- Content -->
                    <h2>${message}</h2>

                    <h1>Modifica Prenotazione di ${cf}</h1>

                    <%--@elvariable id="prenotazione" type="com.example.FrontEnd.FrontEnd.model.Prenotazione" --%>
                        <form:form action="/prenotazioni/modifica-prenotazione" method="post"
                            modelAttribute="prenotazione">
                            <div class="page_content">
                                <div class="filter_content">
                                    <form:hidden class="inp_filter" path="codice" readonly="true" />
                                    <form:hidden class="inp_filter" path="codiceFiscale" readonly="true"
                                        placeholder="CodiceFiscale" />
                                    <!-- <div class="filter_screen">
                                        <h4>Codice</h4>
                                        <form:errors path="codice" cssClass="error" />
                                    </div>

                                    <div class="filter_screen">
                                        <h4>Codice Fiscale</h4>
                                        <form:errors path="codiceFiscale" cssClass="error" />
                                    </div> -->

                                    <div class="filter_screen">
                                        <h4>Data</h4>
                                        <form:input type="datetime-local" class="inp_filter" path="data"
                                            placeholder="Data" />
                                        <form:errors path="data" cssClass="error" />
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

                                    <div class="filter_screen">
                                        <h4>Codici Farmaci</h4>
                                        <form:input class="inp_filter" path="codiceFarmaci"
                                            placeholder="CodiceFarmaci" />
                                        <form:errors path="codiceFarmaci" cssClass="error" />
                                    </div>

                                    <!--
            <div class="filter_screen" id="farmaci">
                <h4>Codici Farmaci</h4>
                <c:forEach items="${codiceFarmaci}" var = "c">
                    <label>
                        <form:checkbox path="codiceFarmaci" value="${c.codice}"></form:checkbox>
                            ${c.nome}
                    </label>
                </c:forEach>


            </div>
-->
                                </div>
                                <form:button class="button button_fill menu" href="">Modifica</form:button>
                            </div>
                        </form:form>
                        <!-- Footer -->
                        <%@include file="/Content/footer.jsp" %>

            </body>

            </html>