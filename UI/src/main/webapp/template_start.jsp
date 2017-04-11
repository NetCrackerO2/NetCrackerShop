<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="/css/style.css">
    <script type="text/javascript" src="../js/jquery-3.2.0.min.js"></script>
    <title><c:out value="${title}"/></title>
</head>
<body>
<div class="wrapper">
    <div id="header_main">
        <div id="logo"><a href="/"><img id="logoImg" src="../image/logo.png"></a></div>
        <div id="home"><a href="/"><img class="icon" src="../image/home.png"></a></div>
        <div id="cart"><a href="cart.jsp"><img class="icon" src="../image/cart.png"></a></div>
        <div id="cntCart"><a href="admin_view.jsp"><img class="icon" src="../image/cntCart.png"></a></div>
        <div id="orders"><a href="orders.jsp"><img class="icon" src="../image/cntCart.png"></a></div>
        <div><label id="userName" class="text1">
            <c:if test="${clientInfo.loggedIn}">
                Клиент: <c:out value="${clientInfo.name}"/><br/>
            </c:if>
        </label></div>
		<div>
			<c:forEach items="${pathStack}" var="item">
				<a href="ВНИКУДА">-><c:out value="${item}"/></a>
			</c:forEach>
		</div>
    </div>
