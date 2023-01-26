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
    <form:form action="/farmacia/nuovo-ordine" method="post" modelAttribute="ordine">
        <div class="page_content">
            <div class="filter_content">
                <div class="filter_screen">
                    <h4>Codice Farmaco</h4>
                    <form:input class="inp_filter" path="codiceFarmaco" placeholder="Codice"/>
                </div>

                <div class="filter_screen">
                    <h4>Quantità</h4>
                    <form:input class="inp_filter" path="quantita" placeholder="Codice"/>
                </div>
            </div>
            <form:button class="button button_fill menu" href="">Ordina</form:button>
        </div>
    </form:form>
    <!-- Footer -->
    <%@include file="/Content/footer.jsp" %>

    </body>

</html>