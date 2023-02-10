<%@ page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Utente - ${Utente.nome} ${Utente.cognome}</title>

    <link rel="stylesheet" href="/css/style.css">
    <script src="/js/script.js"></script>

</head>
<c:choose>
    <c:when test="${ruolo != 'Admin'}">
        <jsp:forward page = "ErrorLogged.jsp" />
    </c:when>
</c:choose>
<body>
    <!-- Header -->
    <%@include file="/Content/header.jsp"%>

     <!-- Content -->

    <h2>Id: ${Utente.id}</h2>
    <h2>${Utente.ruolo}: ${Utente.nome} ${Utente.cognome}</h2>
    <h3>Username: ${Utente.credenziali.user}</h3>
    <h3><a class="button button_outline" href="/utenti/modifica-utente-page/${Utente.id}">Modifica</a></h3>
    <!-- Header -->
    <%@include file="/Content/footer.jsp"%>

</body>
</html>