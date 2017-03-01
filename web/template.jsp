<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href='https://fonts.googleapis.com/css?family=Roboto:700,400,300,500,100&subset=latin,cyrillic' rel='stylesheet' type='text/css'>
	<link rel="stylesheet" type="text/css" href="http://asgreywolf.ru/stupid.css">
	<style>
		h1 {
			margin: 2%;
		}
		nav {
			background-color: #000;
		}
		nav > ul > li > a::after {
			background-color: #FFF;
		}
		article {
			min-width: 200px;
			min-height: 20%;
			width: 20%;
			padding: 30px 30px 30px 30px;
			margin-bottom: 30px;
			margin-left: 10px;
			margin-right: 10px;
		}
		article:last-of-type {
			margin-bottom: 30px;
		}
		desc {
			word-wrap: break-word;
			display: block;
		}
		price {
			color: green;
			display: block;
			font-size: 1.2em;
		}
	</style>
	<title><c:out value="${title}" /></title>
</head>
<body>
	<vertical class="fill">
		<nav class="dark z2">
			<h1>SHOP</h1>
			<ul class="adaptive">
				<c:forEach items="${menu}" var="item">
					<li>
						<a href="${item.url}"><c:out value="${item.title}" /></a>
					</li>
				</c:forEach>
			</ul>
		</nav>
		<main>
			<section class="horizontal wrap">
				<c:forEach items="${data}" var="item">
					<article class="z1">
						<h2><c:out value="${item.title}" /></h2>
						<desc><c:out value="${item.description}" /></desc>
						<price>$<c:out value="${item.price}" /></price>
					</article>
				</c:forEach>
			</section>
		</main>
	</adaptive>
</body>
</html>
