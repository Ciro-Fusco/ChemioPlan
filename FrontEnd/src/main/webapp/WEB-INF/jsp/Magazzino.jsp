<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<c:choose>
    <c:when test="${ruolo != 'Responsabile Farmacia'}">
        <jsp:forward page = "ErrorLogged.jsp" />
    </c:when>
</c:choose>
<body>
<!-- Header -->
<%@include file="/Content/header.jsp" %>

<!-- Content -->
<h2>${message}</h2>
<h2>${ruolo}</h2>

<h1>Magazzino</h1>
<h2><a class="button button_fill" href="/farmacia/disponibili">Visualizza Disponibili</a></h2>
<table>
    <thead>
    <tr>
        <th>Nome</th>
        <th>Dimensione</th>
        <th>Lotti</th>
    </tr>
    </thead>
    <tbody>

    <c:forEach items="${Farmaci}" var="farmaco">

        <tr>
            <td data-label="Nome">${farmaco.nome}</td>
            <td data-label="Dimensione">${farmaco.dimensioneFlacone}</td>
            <td data-label="Lotti">
                <c:set var="i" value="${0}"/>
                <c:forEach items="${farmaco.lotti}" var="lotto">
                    <c:set var="i" value="${i+1}"/>
                    ${lotto.numeroLotto} |
                    <c:choose>
                        <c:when test="${i%3 == 0}">
                            <br>
                        </c:when>
                    </c:choose>

                </c:forEach></td>
            <td>
                <a href="/farmacia/magazzino/${farmaco.codice}">
                    <span class="material-symbols-outlined">open_in_new</span>
                </a>
                <br>
                <a href="/farmacia/elimina/${farmaco.codice}" class="tablink">
                    <span class="material-symbols-outlined">delete</span>
                </a>
            </td>
        </tr>
    </c:forEach>

    </tbody>
</table>
<!-- Header -->
<%@include file="/Content/footer.jsp" %>

</body>

</html>