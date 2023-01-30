<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

<body>
    <!-- Header -->
    <%@include file="/Content/header.jsp" %>

    <!-- Content -->
    <h2>${message}</h2>

    <h1>Aggiungi Paziente</h1>

    <%--@elvariable id="scheda" type="com.example.FrontEnd.FrontEnd.model.SchedaPaziente"--%>
    <form:form action="/pazienti/add-paziente" method="post" modelAttribute="scheda">
        <div class="page_content">
            <div class="filter_content">
                <div class="filter_screen">
                    <h4>Codice Fiscale</h4>
                    <form:input class="inp_filter" path="codiceFiscale" placeholder="Codice" />
                </div>

                <div class="filter_screen" id="farmaci">
                    <h4>Farmaci <br> Dosaggio</h4>
                    <input type="text" id="myInput" onkeyup="myFunction()" placeholder="Cerca per nome..">
                    <table id="myTable">
                        <c:forEach items="${farmaci}" var="f">
                            <tr>
                                <td>
                                    <div class="checkcontainer">
                                        <form:checkbox path="codiceFarmaci" value="${f.codice}" class="checkbox"/>
                                        <label class="checktext">${f.nome}</label>
                                    </div>
                                </td>
                                <td>
                                    <form:input class="inp_filter" path="codiceFarmaci" placeholder="Dosaggio"/>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>

                <div class="filter_screen">
                    <h4>Malattie</h4>
                    <form:input class="inp_filter" path="malattie" placeholder="Malattie" />
                </div>
            </div>
            <form:button class="button button_fill menu" href="">Aggiungi</form:button>
        </div>
    </form:form>

    <!-- Footer -->
    <%@include file="/Content/footer.jsp" %>

    <script>
        function myFunction() {
            var input, filter, table, tr, td, i, txtValue;
            input = document.getElementById("myInput");
            filter = input.value.toUpperCase();
            table = document.getElementById("myTable");
            tr = table.getElementsByTagName("tr");

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