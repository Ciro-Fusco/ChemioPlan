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
    <c:when test="${ruolo != 'Dottore'}">
        <jsp:forward page = "ErrorLogged.jsp" />
    </c:when>
</c:choose>
<body>
    <!-- Header -->
    <%@include file="/Content/header.jsp" %>

    <!-- Content -->
    <h2>${message}</h2>

    <h1>Aggiungi Paziente ${cf}</h1>
    <h2>${nome} ${cognome}</h2>

    <%--@elvariable id="scheda" type="com.example.frontend.model.SchedaPazienteForm" --%>
        <form:form action="/pazienti/add-paziente" method="post" modelAttribute="scheda">
            <div class="page_content">
                <div class="filter_content">
                    <form:hidden class="inp_filter" path="codiceFiscale" value="${cf}" readonly="true" />
                    <form:hidden class="inp_filter" path="nome" value="${nome}" readonly="true" />
                    <form:hidden class="inp_filter" path="cognome" value="${cognome}" readonly="true" />
                    <div class="filter_screen" id="farmaci">
                        <h4>Farmaci <br> Dosaggio</h4>
                        <input type="text" id="inFarmaci" onkeyup="myFunction('inFarmaci', 'FarmaciTable')" placeholder="Cerca per nome..">
                        <table id="FarmaciTable">
                            <c:forEach items="${farmaci}" var="f">
                                <tr>
                                    <td>
                                        <div class="checkcontainer">
                                            <form:input path="farmaci" id="${f.codice}" />
                                            <c:set var="check" value="check${f.codice}"/>
                                            <c:set var="input" value="in${f.codice}"/>
                                            <input type="checkbox" class="checkbox"
                                                onclick="checkboxSelected(${f.codice}, ${check}, ${input})"
                                                id="check${f.codice}" />
                                            <label class="checktext">${f.nome}</label>
                                        </div>
                                    </td>
                                    <td>
                                        <input class="inp_filter" placeholder="Dosaggio"
                                            onkeyup="checkboxSelected(${f.codice}, ${check}, ${input})" id="in${f.codice}"
                                            style="max-width: 80px;min-width: 80px;width: 80px;"
                                            type="number" min="1" />
                                    </td>
                                </tr>
                            </c:forEach>
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
                <form:button class="button button_fill menu" href="">Aggiungi</form:button>
            </div>
        </form:form>

        <!-- Footer -->
        <%@include file="/Content/footer.jsp" %>

    <script>

        function checkboxSelected(key, check, input) {
            console.log(key);
            console.log(check);
            console.log(input);
            var a = typeof key;
            if(a == "number") {
                var field = document.getElementById(key);
              if (check.checked) {
                field.value = "";
                field.value += key + "=";
                field.value += input.value;
                console.log(field.value);
              } else {
                field.value = "";
              }
            }
            else {
              if (check.checked) {
                key.value = "";
                key.value += key.id + "=";
                key.value += input.value;
                console.log(key.value);
              } else {
                key.value = "";
              }
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

</body>

</html>