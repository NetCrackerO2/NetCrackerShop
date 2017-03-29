<!DOCTYPE html>
<%@include file="../env.jsp" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<link rel="stylesheet" type="text/css" href="../css/backet.css">
<html>
<head>
	<title>Интернет магазина "NetCrackerShop"</title>
</head>

<body>
<div id="wrapper">
	<div id="header_main">
		<div id="logo"><a href="Main.jsp" ><img id="logoImg"   src="../image/logo.png"></a></div>
		<div id="home"><a href="login.jsp"><img class="icon"  src="../image/home.png"></a></div>
		<div id="backet"><a href="Backet.jsp"><img class="icon"  src="../image/backet.png"></a></div>
		<div id="cntBacket"><a href=""><img class="icon" src="../image/cntBacket.png"></a></div>
		<div><label id="userName" class="text1">Фамилия пользователя</label></div>
	</div>
	<div id="findBlock" >
		<form action="" class="search">
			<input type="search" name="" placeholder="поиск" class="input" />
			<input type="submit" name="" value="" class="submit" />
		</form>
	</div>
	<div class="middle">

	<div class="container">
			<main class="content">
	<form name="orderButton" action="" method="post">
	<table class="simple-little-table" cellspacing='0'>
	<tr>
		<th>ID</th>
		<th>Наименование </th>
		<th>Количество</th>
		<th>Цена</th>
	</tr><!-- Table Header -->
 	
	<tr>
		<td>1</td>
		<td>Щебень</td>
		<td>1000</td>
		<td>5000$</td>
	</tr>
	<tr>
		<td>2</td>
		<td>Щебень</td>
		<td>1000</td>
		<td>5000$</td>
	</tr>
	<tr>
	<td></td>
	<td></td>
	<td>Итого:</td>
	<td>10000$</td>
	</tr>
</table>
	<div >
		<input type="submit" name="submit" class="orderButton" value="Оформить заказ" class="button" />
	</div>
	</form>

			</main><!-- .content -->
		</div><!-- .container-->
		<aside  id="CategoryBox" >
		<div  id="categoryName" class="text1">Корзина</div>
            <c:set var="categories" value="${categoryBean.getAll()}"/>
            <c:forEach items="${categories}" var="category">
                <div class="categoryItem text1"><c:out value="${category.name}"/></div>
            </c:forEach>
        </aside>
	</div>
	<footer>
	 	
	 </footer>
 </div>
</body>
</html>