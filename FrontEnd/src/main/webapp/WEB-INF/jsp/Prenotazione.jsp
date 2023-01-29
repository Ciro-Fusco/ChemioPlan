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
    <jsp:useBean id="farmacia" class="com.example.FrontEnd.FrontEnd.service.FarmaciaService"/>
    <h3>${message}</h3>
    <table>
        <tr>
            <td>${Prenotazione.codice}</td>
        </tr>
        <tr>
            <td>${Prenotazione.codiceFiscale}</td>
        </tr>
        <tr>
            <td><fmt:formatDate type = "both" dateStyle = "short" timeStyle = "short" value = "${Prenotazione.data}"/></td>
        </tr>
        <tr>
            <td>${Prenotazione.sala}</td>
        </tr>
        <tr>
            <td>${Prenotazione.poltrona}</td>
        </tr>
        <tr>
            <td>
            <c:forEach items="${Prenotazione.codiceFarmaci}" var="codice">
                <c:set var="farmaco" value="${farmacia.getFarmaco(codice)}"/>
                <a href="/farmacia/magazzino/${farmaco.codice}">${farmaco.nome}</a><br>
            </c:forEach>
            </td>
        </tr>
        <tr>
            <td>
            <a class="button menu" href="/prenotazioni/modifica-prenotazione-page/${Prenotazione.codice}">
                <span class="material-symbols-outlined">edit_square</span>
            </a>
            </td>
        </tr>
    </table>

    <!-- Footer -->
    <%@include file="/Content/footer.jsp" %>

    </body>
</html>

