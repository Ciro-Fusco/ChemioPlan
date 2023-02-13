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
    <c:choose>
        <c:when test="${ruolo != 'Dottore'}">
            <jsp:forward page = "ErrorLogged.jsp" />
        </c:when>
    </c:choose>
    <body>
    <!-- Header -->
    <%@include file="/Content/header.jsp"%>

    <!-- Content -->
    <jsp:useBean id="farmacia" class="com.example.frontend.service.FarmaciaService"/>
    <h3>${message}</h3>
    <table>
        <tr>
            <td>Codice: ${Prenotazione.codice}</td>
        </tr>
        <tr>
            <td>Codice Fiscale Paziente: <a href="/pazienti/${Prenotazione.codiceFiscale}">${Prenotazione.codiceFiscale}</a></td>
        </tr>
        <tr>
            <td>Data e ora: <fmt:formatDate type = "both" dateStyle = "short" timeStyle = "short" value = "${Prenotazione.data}"/></td>
        </tr>
        <tr>
            <td>Sala ${Prenotazione.sala}</td>
        </tr>
        <tr>
            <td>Poltrona ${Prenotazione.poltrona}</td>
        </tr>
        <tr>
            <td>
                <a class="button menu" href="/prenotazioni/modifica-prenotazione-page/${Prenotazione.codice}">
                    <span class="material-symbols-outlined">edit_square</span>
                </a>
                <c:choose>
                    <c:when test="${Prenotazione.confermata == false}">
                        <a class="button button_fill" href="/prenotazioni/conferma-prenotazione/${Prenotazione.codice}">Conferma</a>
                    </c:when>
                </c:choose>

            </td>
        </tr>
    </table>

    <!-- Footer -->
    <%@include file="/Content/footer.jsp" %>

    </body>
</html>

