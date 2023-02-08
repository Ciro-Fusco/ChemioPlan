<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Aggiungi Paziente</title>

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
    <h2>${message}</h2>

    <h1>Modifica utente</h1>

    <%--@elvariable id="utente" type="com.example.FrontEnd.FrontEnd.model.Utente" --%>
    <form:form action="/utenti/modifica-utente" method="post" modelAttribute="utente">
        <div class="page_content">
            <div class="filter_content">
                <div class="filter_screen">
                    <h4>ID</h4>
                    <form:input class="inp_filter" path="id" readonly="true"/>
                    <form:errors path="id" cssClass="error" />
                </div>

                <div class="filter_screen">
                    <h4>Nome</h4>
                    <form:input class="inp_filter" path="nome"/>
                    <form:errors path="nome" cssClass="error" />
                </div>

                <div class="filter_screen">
                    <h4>Cognome</h4>
                    <form:input class="inp_filter" path="cognome"/>
                    <form:errors path="cognome" cssClass="error" />
                </div>

                <div class="filter_screen">
                    <h4>Username</h4>
                    <form:input class="inp_filter" path="credenziali.user"/>
                    <form:errors path="credenziali.user" cssClass="error" />
                </div>

                <div class="filter_screen">
                    <h4>Password</h4>
                    <form:password class="inp_filter" path="credenziali.pass"/>
                    <form:errors path="credenziali.pass" cssClass="error" />
                </div>

                <div class="filter_screen">
                    <h4>Ruolo</h4>
                    <form:select class="inp_filter" path="ruolo" placeholder="Codice" >
                        <form:option value="Dottore">Dottore</form:option>
                        <form:option value="Responsabile Farmacia">Responsabile Farmacia</form:option>
                    </form:select>
                </div>
            </div>
            <form:button class="button button_fill menu" href="">Modifica</form:button>
        </div>
    </form:form>

    <!-- Footer -->
    <%@include file="/Content/footer.jsp" %>

</body>

</html>