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
        <th>Farmaci/Dosaggio</th>
        <th>Malatie</th>
    </tr>
    </thead>
    <tbody>

    <jsp:useBean id="farmacia" class="com.example.FrontEnd.FrontEnd.service.FarmaciaService"/>
    <c:forEach items="${Pazienti}" var="paziente">
        <tr>
            <td data-label="Codice Fiscale"><a href="/pazienti/${paziente.codiceFiscale}" class="tablink">${paziente.codiceFiscale}</a></td>
            <td data-label="Farmaci">
                <c:forEach items="${paziente.codiceFarmaci}" var="codice">
                    <c:set var="farmaco" value="${farmacia.getFarmaco(codice.key)}"/>
                    <a href="/farmacia/magazzino/${farmaco.codice}">${farmaco.nome}</a> ${codice.value}<br>
                </c:forEach>
            </td>
            <td data-label="Malattie">${paziente.malattie}</td>
        </tr>
    </c:forEach>

    </tbody>
</table>

<!-- Header -->
<%@include file="/Content/footer.jsp"%>

</body>

</html>