<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Prenotazioni</title>

    <link rel="stylesheet" href="/css/style.css">
    <script src="/js/script.js"></script>

</head>

<body>
<!-- Header -->
<%@include file="/Content/header.jsp"%>

<!-- Content -->
<h1>Prenotazioni</h1>

<table>
    <thead>
    <tr>
        <th>Codice</th>
        <th>Codice Fiscale</th>
        <th>Data</th>
        <th>Sala</th>
        <th>Poltrona</th>
        <th>Codice Farmaci</th>
    </tr>
    </thead>
    <tbody>

    <c:forEach items="${Prenotazioni}" var="prenotazione">
        <tr>
            <td data-label="codice"><a href="/prenotazioni/${prenotazione.codice}" class="tablink">${prenotazione.codice}</a></td>
            <td data-label="codiceFiscale">${prenotazione.codiceFiscale}</td>
            <td data-label="data">${prenotazione.data}</td>
            <td data-label="sala">${prenotazione.sala}</td>
            <td data-label="poltrona">${prenotazione.poltrona}</td>
            <td data-label="codiceFarmaci">${prenotazione.codiceFarmaci}</td>
            <td><a class="button button_outline menu" href="/prenotazioni/elimina/${prenotazione.codice}" class="tablink">Elimina</a></td>
        </tr>
    </c:forEach>

    </tbody>
</table>

<!-- Header -->
<%@include file="/Content/footer.jsp"%>

</body>

</html>
