<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Modifica Paziente</title>

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
        <h2>${message}</h2>

        <h1>Modifica Trattamento</h1>

        <%--@elvariable id="map" type="java.util.HashMap" --%>
        <c:set var="map" value="${schedaMap.farmaci}" />

    <%--@elvariable id="scheda" type="com.example.frontend.model.SchedaPazienteForm" --%>
        <form:form action="/pazienti/modifica-paziente" method="post" modelAttribute="scheda">
            <div class="page_content">
                <h2>${scheda.codiceFiscale}</h2>
                <h2>${scheda.nome} ${scheda.cognome}</h2>
                <div class="filter_content">
                    <form:hidden class="inp_filter" path="codiceFiscale"/>
                    <form:errors path="codiceFiscale" />
                    <form:hidden class="inp_filter" path="nome"/>
                    <form:errors path="codiceFiscale" />
                    <form:hidden class="inp_filter" path="cognome"/>
                    <form:errors path="codiceFiscale" />

                    <div class="filter_screen" id="farmaci">
                        <h4>Farmaci <br> Dosaggio</h4>
                        <input type="text" id="inFarmaci"
                            onkeyup="myFunction('inFarmaci', 'FarmaciTable')"
                            placeholder="Cerca per nome..">
                        <table id="FarmaciTable">
                                <%--@elvariable id="f" type="com.example.frontend.model.SchedaFarmaco" --%>
                            <c:forEach items="${farmaci}" var="f">
                                <c:set var="check" value="check${f.codice}"/>
                                <c:set var="input" value="in${f.codice}"/>
                                <c:choose>
                                    <c:when test="${map.containsKey(f.codice)}">
                                        <tr>
                                            <td>
                                                <div class="checkcontainer">
                                                    <form:hidden path="farmaci" id="${f.codice}" value="${f.codice}=${map.get(f.codice)}" />
                                                    <input type="checkbox" class="checkbox"
                                                        onclick="checkboxSelected(${f.codice}, ${check}, ${input})"
                                                        id="check${f.codice}" checked />
                                                    <label class="checktext">${f.nome}</label>
                                                </div>
                                            </td>
                                            <td>
                                                <input class="inp_filter" placeholder="Dosaggio"
                                                    onkeyup="checkboxSelected(${f.codice}, ${check}, ${input})"
                                                    id="in${f.codice}"
                                                    value="${map.get(f.codice)}" type="number"
                                                    min="1"
                                                    style="max-width:80px;min-width:80px;width:80px;" />
                                            </td>
                                        </tr>
                                    </c:when>
                                    <c:otherwise>
                                        <tr>
                                            <td>
                                                <div class="checkcontainer">
                                                    <form:hidden path="farmaci"
                                                        id="${f.codice}" />
                                                    <input type="checkbox" class="checkbox"
                                                        onclick="checkboxSelected(${f.codice}, ${check}, ${input})"
                                                        id="check${f.codice}" />
                                                    <label class="checktext">${f.nome}</label>
                                                </div>
                                            </td>
                                            <td>
                                                <input class="inp_filter" placeholder="Dosaggio"
                                                    onkeyup="checkboxSelected(${f.codice}, ${check}, ${input})"
                                                    id="in${f.codice}" type="number" min="1"
                                                    style="max-width:80px;min-width:80px;width:80px;" />
                                            </td>
                                        </tr>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>

                            <p class="error">${error}</p>
                        </table>
                    </div>

                    <div class="filter_screen">
                        <h4>Malattie</h4>
                        <input type="text" id="inMalattie"
                            onkeyup="myFunction('inMalattie', 'MalattieTable')"
                            placeholder="Cerca per nome..">
                        <table id="MalattieTable">
                            <c:forEach items="${Malattie}" var="m">
                                <tr>
                                    <td>
                                        <div class="checkcontainer">
                                            <form:checkbox class="checkbox" path="malattie"
                                                value="${m.codiceMalattia}" />
                                            <label class="checktext">${m.nomeMalattia}</label>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
                <form:button class="button button_fill menu" href="">Modifica</form:button>
            </div>
        </form:form>

    <!-- Footer -->
    <%@include file="/Content/footer.jsp" %>

</body>

<script>

    function checkboxSelected(key, check, input) {
        console.log(key);
        console.log(check);
        console.log(input);

        if (check.checked) {
            key.value = "";
            key.value += key.id + "=";
            key.value += input.value;
            console.log(key.value);
        } else {
            key.value = "";
        }
    }

    function myFunction(idIn, idTab) {
        var td, i, txtValue;
        var input = document.getElementById(idIn);
        var filter = input.value.toUpperCase();
        var table = document.getElementById(idTab);
        var tr = table.getElementsByTagName("tr");

        // Loop through all table rows, and hide those who don't match the search query
        for (i = 0; i < tr.length; i++) {
            td = tr[i].getElementsByTagName("td")[0];
            if (td) {
                txtValue = td.textContent || td.innerText;
                if (txtValue.toUpperCase().indexOf(filter) > -1) {
                    tr[i].style.display = "";
                } else {
                    tr[i].style.display = "none";
                }
            }
        }
    }
</script>

</html>