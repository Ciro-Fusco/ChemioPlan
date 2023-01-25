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

        <h1>Magazzino</h1>
        <table>
            <thead>
                <tr>
                    <th>Codice</th>
                    <th>Nome</th>
                    <th>Dosaggio</th>
                    <th>Lotti</th>
                </tr>
            </thead>
            <tbody>

                <c:forEach items="${Farmaci}" var="farmaco">
                    <tr>
                        <td data-label="Codice"><a href="/farmacia/magazzino/${farmaco.codice}" class="tablink">${farmaco.codice}</a></td>
                        <td data-label="Nome">${farmaco.nome}</td>
                        <td data-label="Dosaggio">${farmaco.dosaggio}</td>
                        <td>
                            <c:forEach items="${farmaco.lotti}" var="lotto">
                                ${lotto.numeroLotto}
                            </c:forEach>
                        </td>
                    </tr>
                </c:forEach>

            </tbody>
        </table>
        <!-- Header -->
        <%@include file="/Content/footer.jsp" %>

</body>

</html>