<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Prenotazione</title>

    <link rel="stylesheet" href="/css/style.css">
    <script src="/js/script.js"></script>

</head>
<body>
<!-- Header -->
<%@include file="/Content/header.jsp"%>

<!-- Content -->
<jsp:useBean id="farmacia" class="com.example.frontend.service.FarmaciaService"/>
<h3>${message}</h3>
<table>
    <thead>
    <tr>
        <th>Codice Farmaco</th>
        <th>Dosaggio</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${farmaci}" var="farmaco">
        <tr>
            <td>
                    ${farmaco.key}
            </td>
            <td>
                    ${farmaco.value}
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<!-- Footer -->
<%@include file="/Content/footer.jsp" %>

</body>
</html>

