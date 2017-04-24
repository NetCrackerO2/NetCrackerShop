<!DOCTYPE html>
<%@include file="../temp/env.jsp"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<link rel="stylesheet" type="text/css" href="../css/login.css">
<html>
<head>
	<title>Интернет магазина "NetCrackerShop"</title>
</head>
<body>
<div id="wrapper">
	<div id="header_main">
		<div id="logo"><a href="" ><img id="logoImg"   src="../image/logo.png"></a></div>
	</div>
	<form name="login-form" class="login-form" method="POST" action="Main.jsp">
	<div class="headerLogin">
		<h1>Авторизация</h1>
	</div>

	<div class="contentLogin">
		<input name="login" type="text" class="input username" value="Логин" onfocus="" />
		<%--<input name="password" type="password" class="input password" value="Пароль" onfocus="" />--%>
	</div>
	<div class="footerLogin">

		<input type="submit" name="submit" value="ВОЙТИ" class="button" />
	</div>
	</form>
</div>
	<footer>
		
	</footer>
</body>
</html>