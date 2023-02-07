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

        <h1>Farmaci</h1>
        <div class="wrapper">
            <div class="filter_content">
                <a class="button button_outline menu" href="/farmacia/magazzino">Magazzino</a>
                <a class="button button_outline menu" href="/farmacia/add-farmaco-page">Aggiungi Farmco</a>
                <div style="width:100%;"></div>
                <a class="button button_outline menu" href="/farmacia/nuovo-ordine-page">Ordina Farmaco</a>
                <a class="button button_outline menu" href="/farmacia/ordini-page">Tutti gli ordini</a>
            </div>
        </div>

        <!-- Header -->
        <%@include file="/Content/footer.jsp" %>

</body>

</html>