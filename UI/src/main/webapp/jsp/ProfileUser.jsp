<!DOCTYPE html>
<%@include file="../env.jsp" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<html>
<head>
	<title>Интернет магазина "NetCrackerShop"</title>
</head>

<body>
<div id="wrapper">
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
	<div  id="categoryName" class="text1">Функции</div>
		</aside>
	<div class="main">
		<form class="profile">
			<table class="lk">
				<caption class="text1">Личные данные</caption>
				<tr>
					<td><label>Имя</label> </td>
					<td><input class="textfield" type="text" name="ename" value=""></td>
				</tr>
				<tr>
					<td><label>Дата рождения </label> </td>
					<td><input class="textfield" type="date" name="calendar" value="2012-06-01"></td>
				</tr>
				<br>
				<tr>
					<td><label>Пол </label> </td>
					<td>
						<p>
						<input name="gender" type="radio" value="male"> Мужской</p>
	   					 <p><input name="gender" type="radio" value="female"> Женский</p>
					</td>
				</tr>
				<tr>
					<td><label>Адрес</label> </td>
					<td><input class="textfield" type="text" name="addres" value=""></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" name="submit" class="button1 saveChange" value="Сохранить" class="button" /></td>
				</tr>
			</table>
		</form>
	</div>
	<footer>
	 	
	 </footer>
 </div>
</body>
</html>