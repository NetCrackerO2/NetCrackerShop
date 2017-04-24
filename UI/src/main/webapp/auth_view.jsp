<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="env.jsp" %>
<link rel="stylesheet" type="text/css" href="/css/login.css">
<c:set var="title" value="Авторизация" scope="page"/>
<html lang="ru">
<head>
    <title><c:out value="${title}"/></title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Заголовок </title>


    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/font-awesome.min.css">


    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>

</head>
<body>
<header>
    <div class="container">
        <div class="row">
            <ul class="sign list-inline">
                <c:if test="${!clientInfo.loggedIn}">
                    <li><a href="auth_view.jsp">Войти</a></li>
                    <li><a href="admin_view.jsp">Регистрация</a></li>
                </c:if>
                <c:if test="${clientInfo.loggedIn}">
                    <li><a href="clientsServlet.jsp?logout=">Выйти</a></li>
                </c:if>
            </ul>
        </div>
        <div class="row main_header">
            <div class="col-md-2">
                <h3 class="logo"><a href="index.jsp">NetCrackerShop</a></h3>
            </div>
            <div class="col-md-2 col-md-offset-8  ">
                <ul class="user list-inline">
                    <c:if test="${clientInfo.loggedIn}">
                        <li>Клиент:<a href="user_profile.jsp"><c:out value="${clientInfo.name}"/></a></li>
                        <li><a href="cart_view.jsp">Корзина</a></li>
                    </c:if>
                </ul>
            </div>
        </div>
    </div>
</header>
<!-- Главный Экран- -->
<section id="content">
    <div class="container">
        <div class="row">
            <div class="col-md-6 col-md-offset-3">
                <div class="main">
                    <form name="login-form" class="login-form" method="POST" action="/auth.jsp">
                        <div class="headerLogin">
                            <h1>Авторизация</h1>
                        </div>
                        <div class="contentLogin">
                            <input name="login" type="text" class="input username" placeholder="Логин"/>
                        </div>
                        <div class="footerLogin">
                            <input type="submit" name="submit" value="ВОЙТИ" class="btn-primary button"/>
                        </div>
                        <c:if test="${isError==true}">
                            <div>
                                <p style="color:red;" align="center"><c:out value="${errorMessage}"/></p>
                            </div>
                        </c:if>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>

<%@include file="template_end.jsp" %>
