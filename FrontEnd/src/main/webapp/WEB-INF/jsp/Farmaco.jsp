<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <%@ taglib uri="jakarta.tags.core" prefix="c" %>
    <%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>

            <meta charset="UTF-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Farmacia</title>

            <link rel="stylesheet" href="/css/style.css">
            <script src="/js/script.js"></script>

        </head>

        <body>
            <!-- Header -->
            <%@include file="/Content/header.jsp" %>

                <!-- Content -->

                <h1>Nome Farmaco: ${Farmaco.nome}</h1>
                <h4>Codica Farmaco: ${Farmaco.codice}</h4>
                <h4>Quantita`: ${Farmaco.quantita}</h4>
                <h4>Numero Lotto: ${Farmaco.numeroLotto}</h4>
                <h4>Data Scadenza:<fmt:formatDate value="${Farmaco.scadenzaLotto}" pattern="yyyy-MM-dd"/></h4>

                <!-- Header -->
                <%@include file="/Content/footer.jsp" %>

        </body>

        </html>