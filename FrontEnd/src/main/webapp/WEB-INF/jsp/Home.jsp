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
            <h1>Ciao<br>${Name}<br>Benvenuto in ChemioPlan</h1>

            <h4>E-Mail</h4>
            <input type="input" placeholder="yourmail@gmail.com" style="width: 100%;">
            <h4>Password</h4>
            <input type="password" placeholder="password" style="width: 100%;">

            <a class="button button_fill menu" href="">Log In</a>

            <a class="link" href="" style="display: block;">Non Sei Ancora Registrato? Sing Up</a>

        </div>
        <div class="home_image" style="background-image: url('/Content/Home_Image.jpg');"> </div>
    </div>


    <!-- Header -->
    <%@include file="/Content/footer.jsp"%>

</body>

</html>