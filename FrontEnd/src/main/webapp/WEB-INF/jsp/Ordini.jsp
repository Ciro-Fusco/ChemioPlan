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
            <c:when test="${ruolo != 'Responsabile Farmacia' or ruolo != 'Dottore'}">
                <jsp:forward page = "ErrorLogged.jsp" />
            </c:when>
        </c:choose>

        <body>
            <!-- Header -->
            <%@include file="/Content/header.jsp" %>

                <!-- Content -->

                <h1>Lista Ordini</h1>
                <table>
                    <thead>
                        <tr>
                            <th>Codice</th>
                            <th>Codice Farmaco</th>
                            <th>Quantita</th>
                            <th>Stato</th>
                        </tr>
                    </thead>
                    <tbody>

                        <c:forEach items="${ordini}" var="ordine">
                            <tr>
                                <td data-label="Codice">${ordine.id}</td>
                                <td data-label="Codice Farmaco">${ordine.codiceFarmaco}</td>
                                <td data-label="Quantita">${ordine.quantita}</td>
                                <td data-label="Stato">${ordine.stato}</td>
                            </tr>
                        </c:forEach>

                    </tbody>
                </table>
                <!-- Header -->
                <%@include file="/Content/footer.jsp" %>

        </body>

        </html>