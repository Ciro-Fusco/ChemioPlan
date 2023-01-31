<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Prenotazioni</title>

    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
    <script src="/js/script.js"></script>

</head>

<body>
<!-- Header -->
<%@include file="/Content/header.jsp"%>

<!-- Content -->
<h1>Prenotazioni</h1>

<div class="page_content">
    <input type="text" id="myInput" onkeyup="myFunction()" placeholder="Cerca per data">
</div>

<table id="myTable">
    <thead>
    <tr>
        <th>Paziente</th>
        <th>Sala</th>
        <th>Poltrona</th>
        <th>Data</th>
        <th>Farmaci</th>
    </tr>
    </thead>
    <tbody>

    <jsp:useBean id="farmacia" class="com.example.FrontEnd.FrontEnd.service.FarmaciaService"/>
    <c:forEach items="${Prenotazioni}" var="prenotazione">
        <tr>
            <td data-label="codiceFiscale"><a href="/pazienti/${prenotazione.codiceFiscale}">${prenotazione.codiceFiscale}</a></td>
            <td data-label="sala">${prenotazione.sala}</td>
            <td data-label="poltrona">${prenotazione.poltrona}</td>
            <td data-label="data" class="data">
                <fmt:formatDate type = "both" dateStyle = "short" timeStyle = "short" value = "${prenotazione.data}"/>
            </td>
            <td data-label="codiceFarmaci">
                <c:forEach items="${prenotazione.codiceFarmaci}" var="codice">
                    <c:set var="farmaco" value="${farmacia.getFarmaco(codice)}"/>
                    <a href="/farmacia/magazzino/${farmaco.codice}">${farmaco.nome}</a><br>
                </c:forEach>
            </td>
            <td data-label="codice">
                <a href="/prenotazioni/${prenotazione.codice}" class="tablink">
                    <span class="material-symbols-outlined">open_in_new</span>
                </a>
                <br>
                <a href="/prenotazioni/elimina/${prenotazione.codice}">
                    <span class="material-symbols-outlined">delete</span>
                </a>
            </td>
        </tr>
    </c:forEach>

    </tbody>
</table>

<!-- Header -->
<%@include file="/Content/footer.jsp"%>

</body>

<script>
  function myFunction() {
    var input, filter, table, tr, td, i, txtValue;
    input = document.getElementById("myInput");
    filter = input.value.toUpperCase();
    table = document.getElementById("myTable");
    tr = table.getElementsByTagName("tr");

    // Loop through all table rows, and hide those who don't match the search query
    for (i = 0; i < tr.length; i++) {
      td = tr[i].getElementsByClassName("data")[0];
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
