<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <title><c:out value="${title}"/></title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

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
                        <li><a href="orders.jsp">Заказы</a></li>
                        <li><a href="user_profile.jsp">Личный кабинет</a></li>
                    </c:if>
                </ul>
            </div>
        </div>
    </div>
</header>

<nav>
    <div class="container">
        <div class="row">
            <div class="text-right">
                <form action="SearchServlet" class="search">
                    <table>
                        <tr>
                            <td><input type="search" name="findInput" placeholder="поиск" class="input"/></td>
                            <td><input type="submit" name="findProduct" value="" class="findButton"/></td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </div>
    <div class="row">
        <ul class="list-inline navigate">
            <li><a href="categories.jsp">Категории</a></li>
            <li><a href="search.jsp">Поиск</a></li>
            <c:if test="${clientInfo.loggedIn}">
                <li><a href="user_profile.jsp">Личный кабинет</a></li>
            </c:if>
            <li><a href="admin_view.jsp">Админка</a></li>
        </ul>
    </div>
</nav>
<section id="content">
    <div class="container">
