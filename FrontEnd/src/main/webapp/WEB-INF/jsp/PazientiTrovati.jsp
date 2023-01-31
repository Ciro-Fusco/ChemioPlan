<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pazienti</title>

    <link rel="stylesheet" href="/css/style.css">
    <script src="/js/script.js"></script>

</head>

<body>
<!-- Header -->
<%@include file="/Content/header.jsp"%>

<!-- Content -->
<h1>Pazienti</h1>

<table>
    <thead>
    <tr>
        <th>Codice Fiscale</th>
        <th>Nome</th>
        <th>Cognome</th>
        <th>Indirizzo</th>
        <th>Data di nascita</th>
        <th>Luogo di Nascita</th>

    </tr>
    </thead>
    <tbody>

    <%--@elvariable id="paziente" type="com.example.FrontEnd.FrontEnd.model.Paziente" --%>
    <c:forEach items="${pazienti}" var="paziente">
        <tr>
            <td data-label="Codice Fiscale"><a href="/pazienti/${paziente.codiceFiscale}" class="tablink">${paziente.codiceFiscale}</a></td>
            <td data-label="Nome">${paziente.nome}</td>
            <td data-label="Cognome">${paziente.cognome}</td>
                <%--@elvariable id="indirizzo" type="com.example.FrontEnd.FrontEnd.model.Indirizzo" --%>
            <c:set var="indirizzo" value="${paziente.indirizzo}"></c:set>
            <td data-label="Indirizzo">${indirizzo.via}, ${indirizzo.citta}<br>
                    ${indirizzo.cap}, ${indirizzo.paese}</td>
            <td data-label="Data di nascita">${paziente.dataNascita}</td>
            <td data-label="Città di Nascità">${paziente.luogoNascita}</td>
            <td data-label = "Aggiungi"><a href="/pazienti/add-paziente-page/${paziente.codiceFiscale}"><span class="material-symbols-outlined">person_add</span></a></td>
        </tr>
    </c:forEach>

    </tbody>
</table>

<!-- Header -->
<%@include file="/Content/footer.jsp"%>

</body>

</html>