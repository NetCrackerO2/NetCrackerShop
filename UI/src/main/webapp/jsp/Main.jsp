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
		<div id="home"><a href="ProfileUser.jsp"><img class="icon"  src="../image/home.png"></a></div>
		<div id="cart"><a href="Cart.jsp"><img class="icon" src="../image/cart.png"></a></div>
		<div id="cntCart"><a href=""><img class="icon" src="../image/cntCart.png"></a></div>
		<div><label id="userName" class="text1"><a href="AdminPageQ.jsp">Админка</a></label></div>
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
		<div  id="categoryName" class="text1">Категории</div>
           <!--  <c:set var="categories" value="${categoryBean.getAll()}"/>
            <c:forEach items="${categories}" var="category">
                <div class="categoryItem text1"><c:out value="${category.name}"/></div>
            </c:forEach> -->
        </aside>
		<div class="main">
				<!-- <c:set var="products" value="${productBean.getAll()}"/>
				<c:forEach items="${products}" var="item">
					<article class="z1">
						<h2><c:out value="${item.name}" /></h2>
						<desc><c:out value="${item.description}" /></desc>
						<price>$<c:out value="${item.price}" /></price>
					</article>
				</c:forEach> -->
		</div>
</div>
	<!-- <footer>
	 	
	 </footer> -->
 
</body>
</html>