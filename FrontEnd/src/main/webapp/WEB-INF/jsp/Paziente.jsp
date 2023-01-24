<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Paziente</title>

    <link rel="stylesheet" href="/css/style.css">
    <script src="/js/script.js"></script>

</head>

<body>
<!-- Header -->
<%@include file="/Content/header.jsp"%>

<!-- Content -->

<h1>Id: ${Paziente.id}</h1>
<h1>${Paziente.codiceFiscale}</h1>
<h3>${Paziente.codiceFarmaci}</h3>
<h3>${Paziente.malattie}</h3>

<!-- Header -->
<%@include file="/Content/footer.jsp"%>

</body>

</html>