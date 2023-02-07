<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Prenotazioni Home</title>

    <link rel="stylesheet" href="/css/style.css">
    <script src="/js/script.js"></script>

</head>
<c:choose>
    <c:when test="${ruolo != 'Dottore'}">
        <jsp:forward page = "ErrorLogged.jsp" />
    </c:when>
</c:choose>
<body>
    <!-- Header -->
    <%@include file="/Content/header.jsp" %>

        <!-- Content -->

        <h1>Prenotazioni</h1>
        <div class="wrapper">
            <div class="filter_content">
                <a class="button button_outline menu" href="/prenotazioni/all">Visualizza Prenotazioni</a>
                <a class="button button_outline menu" href="/prenotazioni/add-prenotazione-page">Aggiungi
                    Prenotazione</a>
            </div>
        </div>

        <!-- Header -->
        <%@include file="/Content/footer.jsp" %>

</body>

</html>