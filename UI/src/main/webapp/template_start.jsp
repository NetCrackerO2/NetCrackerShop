<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="/css/style.css">
    <script type="text/javascript" src="../js/jquery-3.2.0.min.js"></script>
    <title><c:out value="${title}" /></title>
</head>
<body>
<div class="wrapper">
    <div id="header_main">
        <div id="logo"><a href="/" ><img id="logoImg" src="../image/logo.png"></a></div>
        <div id="home"><a href="auth.jsp"><img class="icon" src="../image/home.png"></a></div>
        <div id="backet"><a href="cart.jsp"><img class="icon"  src="../image/backet.png"></a></div>
        <div id="cntBacket"><a href=""><img class="icon" src="../image/cntBacket.png"></a></div>
        <div><label id="userName" class="text1">
            <c:if test="${clientInfo.loggedIn}" >
                Client Id: <c:out value="${clientInfo.id}" /><br />
            </c:if>
        </label></div>
    </div>
    <nav>
        <div id="findBlock" >
            <form action="" class="search">
                <table>
                    <tr>
                        <td><input type="search" name="" placeholder="поиск" class="input" /></td>
                        <td><input type="submit" name="" value="" class="findButton" /></td>
                    </tr>
                </table>
            </form>
        </div>
    </nav>
    <aside>
        <div id="categoryName" class="text1"><c:out value="${title}" /></div>
        <div class="text3">Что-то будет...</div>
    </aside>
    <div class="main">
