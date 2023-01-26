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

<body>
    <!-- Header -->
    <%@include file="/Content/header.jsp" %>

        <!-- Content -->

        <h1>Farmaci</h1>
        <div class="filter_content">
            <a class="button button_outline menu" href="/farmacia/magazzino">Magazzino</a>
            <a class="button button_outline menu" href="/farmacia/add-farmaco-page">Aggiungi Farmco</a>
            <a class="button button_outline menu" href="">Ordina Farmaco</a>
        </div>

        <!-- Header -->
        <%@include file="/Content/footer.jsp" %>

</body>

</html>