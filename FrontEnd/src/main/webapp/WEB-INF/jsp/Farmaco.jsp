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
    <h2>${message}</h2>
    <h3>Nome Farmaco: ${Farmaco.nome}</h3>
    <h4>Codica Farmaco: ${Farmaco.codice}</h4>
    <h4>Dosaggio: ${Farmaco.dosaggio}</h4>
    <br>
    <table>
        <thead>
            <th>Numero Lotto</th>
            <th>Scadenza</th>
            <th>Quantità</th>
        </thead>
        <tbody>
        <c:forEach items="${Farmaco.lotti}" var="lotto">
            <tr>
                <td>${lotto.numeroLotto} </td>
                <td><fmt:formatDate value="${lotto.scadenzaLotto}" pattern="yyyy-MM-dd"/></td>
                <td>${lotto.quantita}</td>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="3"><a class="button button_outline menu" href="/farmacia/add-lotto-page/${Farmaco.codice}">Aggiungi Lotto</a></td>
        </tr>
        </tbody>
    </table>

    <!-- Header -->
    <%@include file="/Content/footer.jsp" %>

</body>

</html>