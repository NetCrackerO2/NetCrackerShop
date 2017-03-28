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
		<div id="backet"><a href=""><img class="icon"  src="../image/backet.png"></a></div>
		<div id="cntBacket"><a href=""><img class="icon" src="../image/cntBacket.png"></a></div>
		<div><label id="userName" class="text1">Фамилия пользователя</label></div>
	</div>

	<div class="middle">
		<div id="findBlock" > 
		<form action="" class="search">
  			<input type="search" name="" placeholder="поиск" class="input" />
  			<input type="submit" name="" value="" class="submit" />
		</form>
	</div>
	<div class="container">

			<main class="content">
				<c:set var="products" value="${productBean.getAll()}"/>
				<c:forEach items="${products}" var="item">
					<article class="z1">
						<h2><c:out value="${item.name}" /></h2>
						<desc><c:out value="${item.description}" /></desc>
						<price>$<c:out value="${item.price}" /></price>
					</article>
				</c:forEach>

			</main><!-- .content -->
		</div><!-- .container-->
		<aside  id="CategoryBox" >
		<div  id="categoryName" class="text1">Категории</div>
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