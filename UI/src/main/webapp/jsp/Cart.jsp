<!DOCTYPE html>
<%@include file="../env.jsp" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<link rel="stylesheet" type="text/css" href="../css/Backet.css">
<html>
<head>
	<title>Интернет магазина "NetCrackerShop"</title>
</head>

<body>
<div class="wrapper">
	<div id="header_main">
		<div id="logo"><a href="Main.jsp" ><img id="logoImg"   src="../image/logo.png"></a></div>
		<div id="home"><a href="login.jsp"><img class="icon"  src="../image/home.png"></a></div>
		<div id="cart"><a href="Cart.jsp"><img class="icon" src="../image/cart.png"></a></div>
		<div id="cntCart"><a href=""><img class="icon" src="../image/cntCart.png"></a></div>
		<div><label id="userName" class="text1">Фамилия пользователя</label></div>
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
		<div  id="categoryName" class="text1">Корзина</div>
		<div class="text3">Что-то будет...</div>
	</aside>
	<div class="main">
	<form id="backetForm" name="orderButton" action="" method="post">
	<table class="simple-little-table" cellspacing='0'>
	<tr>
		<th>ID</th>
		<th>Наименование </th>
		<th>Количество</th>
		<th>Цена</th>
	</tr><!-- Table Header -->
 	<tbody>
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
	<td><div >
		<input type="submit" name="submit" class="button1" value="Оформить заказ" class="button" />
	</div></td>
	</tr>
	</tbody>
	</table>
	</form>
	</div>
	<footer>
	 	
	 </footer>
</div>
</body>
</html>