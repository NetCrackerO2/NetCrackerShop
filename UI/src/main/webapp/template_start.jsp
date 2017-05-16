<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%request.setCharacterEncoding("UTF-8");%>
<!DOCTYPE html>
<html lang="ru">
<head>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.15/angular.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/func.js"></script>

    <title><c:out value="${title}"/></title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/font-awesome.min.css">
</head>
<body>
<header>
    <div class="container">
        <div class="row">
            <ul class="sign list-inline">
                <c:if test="${!clientInfo.loggedIn}">
                    <li><a href="auth.jsp">Войти</a></li>
                    <li><a href="register.jsp">Регистрация</a></li>
                </c:if>
                <c:if test="${clientInfo.loggedIn}">
                    <li><a href="clientInfo.jsp?logout=">Выйти</a></li>
                </c:if>
            </ul>
        </div>
        <div class="row main_header">
            <div class="col-md-2">
                <h3 class="logo"><a href="index.jsp">NetCrackerShop</a></h3>
            </div>
            <div class="col-md-3 col-md-offset-9  ">
                <ul class="user list-inline">
                    <c:if test="${clientInfo.loggedIn}">
                        <li>Здравствуйте, <a href="user_profile.jsp"><c:out
                                value="${clientBean.get(clientInfo.id).name}"/></a></li>
                        <li><a href="cart_view.jsp"><img class="icon" src="../image/cart.png"></a></li>
                    </c:if>
                </ul>
            </div>
        </div>
    </div>
</header>

<nav>

