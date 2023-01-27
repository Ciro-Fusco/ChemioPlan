<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home</title>

    <link rel="stylesheet" href="/css/style.css">
    <script src="/js/script.js"></script>

</head>

<body>
<!-- Header -->
<%@include file="/Content/header.jsp"%>

<!-- Content -->
<div class="home_container">
    <div class="login_box">

        <img alt="" style="max-width: 80px;display: block;margin: auto;"
             src="/Content/Logo.png">

        <h1></h1>
        <h1><br>Benvenuto in ChemioPlan</h1>
        <h3>${message}</h3>
    </div>
    <div class="home_image" style="background-image: url('/Content/Home_Image.jpg');"> </div>
</div>



<!-- Header -->
<%@include file="/Content/footer.jsp"%>

</body>

</html>