<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Utente - ${Utente.nome} ${Utente.cognome}</title>

    <link rel="stylesheet" href="/css/style.css">
    <script src="/js/script.js"></script>

</head>

<body>
    <!-- Header -->
    <%@include file="/Content/header.jsp"%>

     <!-- Content -->

    <h1>Id: ${Utente.id}</h1>
    <h1>${Utente.ruolo}: ${Utente.nome} ${Utente.cognome}</h1>
    <h3>Questa e` CyberSecurity</h3>
    <h3>Username: ${Utente.credenziali.user}</h3>
    <h3>Password: ${Utente.credenziali.pass}</h3>

    <!-- Header -->
    <%@include file="/Content/footer.jsp"%>

</body>

</html>