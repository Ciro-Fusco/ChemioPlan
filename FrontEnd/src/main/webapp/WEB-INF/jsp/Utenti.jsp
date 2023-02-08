<%@ page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Utenti</title>

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
    <%@include file="/Content/header.jsp" %>

    <!-- Content -->
    <h1>Utenti</h1>
    <h3><a class="button button_fill" href="/utenti/add-utente-page">Aggiungi utente</a></h3>
    <table>
        <thead>
            <tr>
                <th>Id</th>
                <th>Nome</th>
                <th>Ruolo</th>
            </tr>
        </thead>
        <tbody>

            <c:forEach items="${Utenti}" var="utente">
                <tr>
                    <td data-label="Id">${utente.id}</td>
                    <td data-label="Nome"><a href="/utenti/${utente.id}" class="tablink">${utente.nome}
                            ${utente.cognome}</a></td>
                    <td data-label="Ruolo">${utente.ruolo}</td>
                    <c:choose>
                        <c:when test="${utente.ruolo != 'Admin'}">
                            <td><a href="/utenti/elimina/${utente.id}"><span class="material-symbols-outlined">delete</span></a></td>
                        </c:when>
                    </c:choose>

                </tr>
            </c:forEach>

        </tbody>
    </table>

    <!-- Header -->
    <%@include file="/Content/footer.jsp" %>

</body>

</html>