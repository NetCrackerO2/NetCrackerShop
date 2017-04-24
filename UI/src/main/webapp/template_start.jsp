<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
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

<nav>
    <div class="container">
        <div class="row">
            <ul class="list-inline navigate">
                <li><a href="categories.jsp">Категории</a></li>
                <li><a href="search.jsp">Поиск</a></li>
                <li><a href="admin_view.jsp">Админка</a></li>
            </ul>
        </div>
    </div>
</nav>
