<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pazienti Home</title>

    <link rel="stylesheet" href="/css/style.css">
    <script src="/js/script.js"></script>

</head>

<body>
    <!-- Header -->
    <%@include file="/Content/header.jsp" %>

        <!-- Content -->

        <h1>Pazienti</h1>
        <div class="filter_content">
            <a class="button button_outline menu" href="/pazienti">Visualizza Pazienti</a>
            <a class="button button_outline menu" href="">Aggiungi Paziente</a>
            <a class="button button_outline menu" href="" style="max-width:200px;min-width:200px;">Modifica Trattamento</a>
        </div>

        <!-- Header -->
        <%@include file="/Content/footer.jsp" %>

</body>

</html>