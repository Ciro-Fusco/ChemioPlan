<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <h3>${message}</h3>
    <table>
        <tr>
            <td>Codice: ${Farmaco.codice}</td>
        </tr>
        <tr>
            <td>Nome Farmaco: ${Farmaco.nome}</td>
        </tr>
        <tr>
            <td>Dimensione: ${Farmaco.dimensioneFlacone}</td>
        </tr>
        <tr>
            <td>Il farmaco dura ${Farmaco.scadenzaDopoApertura} giorni dopo l'aperura</td>
        </tr>
        <tr>
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
                        <td><fmt:formatDate value="${lotto.scadenzaLotto}" pattern="dd-MM-yyyy"/></td>
                        <td>${lotto.quantita}</td>
                        <td>
                            <a href="/farmacia/modifica-lotto-page/${Farmaco.codice}/${lotto.numeroLotto}">
                                <span class="material-symbols-outlined">edit_square</span>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="4">
                        <a class="button button_outline menu" href="/farmacia/add-lotto-page/${Farmaco.codice}">Aggiungi Lotto</a>
                    </td>
                </tr>
                <tr>
                    <td colspan="4">
                        <a class="button button_outline menu" href="/farmacia/modifica-farmaco-page/${Farmaco.codice}">Modifica Farmaco</a>
                    </td>
                </tr>
                </tbody>
            </table>
        </tr>
    </table>
    <!-- Header -->
    <%@include file="/Content/footer.jsp" %>

</body>

</html>