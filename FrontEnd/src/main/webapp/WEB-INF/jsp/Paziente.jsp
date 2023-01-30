<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Paziente</title>

    <link rel="stylesheet" href="/css/style.css">
    <script src="/js/script.js"></script>

</head>

<body>
    <!-- Header -->
    <%@include file="/Content/header.jsp"%>

    <!-- Content -->
    <h3>${message}</h3>
    <jsp:useBean id="farmacia" class="com.example.FrontEnd.FrontEnd.service.FarmaciaService"/>
    <table>
        <tr>
            <td>${Paziente.codiceFiscale}</td>
        </tr>
        <tr>
            <td>
                <c:forEach items="${Paziente.farmaci}" var="codice">
                    <c:set var="farmaco" value="${farmacia.getFarmaco(codice.key)}"/>
                    <a href="/farmacia/magazzino/${farmaco.codice}">${farmaco.nome}</a> ${codice.value}ml<br>
                </c:forEach>
            </td>
        </tr>
        <tr>
            <td>
                <c:forEach items="${Paziente.malattie}" var="malattia">
                    ${malattia}<br>
                </c:forEach>
            </td>
        </tr>
        <tr>
            <td>
                <a class="button menu" href="/pazienti/modifica-paziente-page/${Paziente.codiceFiscale}">
                    <span class="material-symbols-outlined">edit_square</span>
                </a>
            </td>
        </tr>
    </table>
    <div class="filter_content">

    </div>
    <!-- Header -->
    <%@include file="/Content/footer.jsp"%>

</body>

</html>