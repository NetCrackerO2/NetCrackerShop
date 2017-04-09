<!DOCTYPE html>
<%@include file="../env.jsp" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<link rel="stylesheet" type="text/css" href="../css/Backet.css">
<html>
<head>
	<script type="text/javascript" src="../js/jquery-3.2.0.min.js"></script>
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
		<!-- <button class="categoryRef">sss</button> -->
		<div id="goodsRef" class="select1 text3" >Работа с товаром</div>
		<div id="categoryRef" class="select1 text3">Работа с категориями</div>
	</aside>
	<div class="main">
		<form id="goodsForm" name="goodsForm" action="" method="">
	<table id="goodsTable" class="simple-little-table" cellspacing='0'>
	<tr>
		<th>ID</th>
		<th>Наименование</th>
		<th>Количество</th>
		<th>Цена</th>
	</tr><!-- Table Header -->
 	
	<tr>
		<td>121312</td>
		<td>Щебень</td>
		<td>1000</td>
		<td>5000$</td>
		<td><input type="submit" name="del" class="button2 button1" value="Edit" /></td>
		<td><input type="submit" name="del" class="delete button2 button1" value="Del" /></td>
	</tr>
	<tr>
		<td>124124</td>
		<td>Щебень</td>
		<td>1000</td>
		<td>5000%</td>
		<td><input type="submit" name="del" class="button2 button1" value="Edit" onclick="" /></td>
		<td><input type="submit" name="del" class="delete button2 button1" value="Del" /></td>
	</tr>
</table>
	<div >
		<input type="submit" name="submit" class="button1" value="Сохранить" />
	</div>
	</form>
	<form id="categoryForm" name="categoryForm" action="" method="">
	<table id="categoryTable" class="simple-little-table" cellspacing='0'>
	<tr>
		<th>ID</th>
		<th>Наименование</th>
		<th>Количество товаров</th>
	</tr><!-- Table Header -->
 	
	<tr>
		<td>121312</td>
		<td>Щебень</td>
		<td>1000</td>
		<td><input type="submit" name="del" class="button2 button1" value="Edit" /></td>
		<td><input type="submit" name="del" class="delete button2 button1" value="Del" /></td>
	</tr>
</table>
	<div >
		<input type="submit" name="submit" class="button1" value="Сохранить"/>
	</div>
	</form>
	</div>
<!-- 	<footer>
	 </footer> -->
 </div>
</body>
<script src="../js/func.js"></script>
</html>