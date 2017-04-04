<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href='https://fonts.googleapis.com/css?family=Roboto:700,400,300,500,100&subset=latin,cyrillic' rel='stylesheet' type='text/css'>
	<link rel="stylesheet" type="text/css" href="http://asgreywolf.ru/stupid.css">
	<link rel='stylesheet' type='text/css' href='styles.css'/>
	<title><c:out value="${title}" /></title>
</head>
<body>
	<vertical class="fill">
		<nav class="dark z2">
			<h1>SHOP</h1>
			<ul class="adaptive">
				<li>
					<a href="/">Главная</a>
				</li>
				<li>
					<a href="/admin.jsp">Администратор</a>
				</li>
				<li>
					<a href="/cart.jsp">Корзина</a>
				</li>
			</ul>
		</nav>
		<main>
			<div>
				<c:if test="${clientInfo.loggedIn}" >
					Client Id: ${clientInfo.id}<br />
				</c:if>
				<%-- В корзине:
				<span id="shopped">${client.orderItems}</span> --%>
			</div>
			<section class="horizontal wrap">
