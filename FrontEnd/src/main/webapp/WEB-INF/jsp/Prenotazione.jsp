<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Prenotazione</title>

        <link rel="stylesheet" href="/css/style.css">
        <script src="/js/script.js"></script>

    </head>

    <body>
    <!-- Header -->
    <%@include file="/Content/header.jsp"%>

    <!-- Content -->

    <h3>${message}</h3>
    <h1>${Prenotazione.codice}</h1>
    <h3>${Prenotazione.codiceFiscale}</h3>
    <h3><fmt:formatDate value="${Prenotazione.data}" pattern="dd-MM-yyyy"/></h3>
    <h3>${Prenotazione.sala}</h3>
    <h3>${Prenotazione.poltrona}</h3>
    <h3>${Prenotazione.codiceFarmaci}</h3>
    <div class="filter_content">
        <a class="button button_outline menu" href="/prenotazioni/modifica-prenotazione-page/${Prenotazione.codice}">Modifica Prenotazione</a>
    </div>

        <!-- Footer -->
    <%@include file="/Content/footer.jsp" %>

    </body>
</html>

