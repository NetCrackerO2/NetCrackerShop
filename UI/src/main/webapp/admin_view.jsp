<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="env.jsp" %>

<%@include file="reqauth.jsp" %>

<c:set var="title" value="Admin panel" scope="page"/>
<c:set var="pathStack" value="${['Admin']}" scope="page"/>
<link rel="stylesheet" type="text/css" href="/css/cart.css">
<%@include file="template_start.jsp" %>
<nav>
    <div id="findBlock">
        <form action="" class="search">
            <table>
                <tr>
                    <td><input type="search" name="" placeholder="поиск" class="input"/></td>
                    <td><input type="submit" name="" value="" class="findButton"/></td>
                </tr>
            </table>
        </form>
    </div>
</nav>
<aside>
    <div id="categoryName" class="text1"><c:out value="${title}"/></div>
    <!-- <button class="categoryRef">sss</button> -->
    <div id="goodsRef" class="select1 text3">Работа с товаром</div>
    <div id="categoryRef" class="select1 text3">Работа с категориями</div>
    <div id="clientsRef" class="select1 text3">Работа с клиентами</div>
</aside>
<div class="main">
    <table id="productTable" class="simple-little-table" cellspacing='0'>
        <tr>
            <th>ID</th>
            <th>Наименование</th>
            <th>Количество</th>
            <th>Цена</th>
        </tr><!-- Table Header -->
        <c:forEach items="${productBean.getAll()}" var="item">
            <tr>
                <td name="idProduct" class="editable"><c:out value="${item.id}"/></td>
                <td name="nameProduct" class="editable"><c:out value="${item.name}"/></td>
                <td name="countProduct" class="editable"><c:out value="${item.count}"/></td>
                <td name="priceProduct" class="editable">$<c:out value="${item.price}"/></td>
                <td><input type="submit" name="edit" class="button2 button1 editButton" value="Edit"/></td>
                <td>
                    <a href="<c:url value="/productsServlet.jsp?removeProduct=&productId=${item.id}"/>">Удалить</a>
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td><input type="submit" name="add" class="add button2 button1" value="Add"/></td>
        </tr>

    </table>
    <table id="categoryTable" class="simple-little-table" cellspacing='0'>
        <tr>
            <th>ID</th>
            <th>Наименование</th>
        </tr><!-- Table Header -->
        <c:forEach items="${categoryBean.getAll()}" var="item">
            <tr>
                <td class="editable"><c:out value="${item.id}"/></td>
                <td class="editable"><c:out value="${item.name}"/></td>
                <td><input type="submit" name="edit" class="button2 button1 editButton" value="Edit"/></td>
                <td>
                    <a href="<c:url value="/categoriesServlet.jsp?removeCategory=&categoryId=${item.id}"/>">Удалить</a>
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td><input type="submit" name="add" class="add button2 button1" value="Add"/></td>
        </tr>
    </table>
    <table id="clientsTable" class="simple-little-table" cellspacing='0'>
        <tr>
            <th>ID</th>
            <th>Имя</th>
            <th>Адрес</th>
        </tr><!-- Table Header -->
        <c:forEach items="${clientBean.getAll()}" var="item">
            <tr>
                <td class="editable"><c:out value="${item.id}"/></td>
                <td class="editable"><c:out value="${item.name}"/></td>
                <td class="editable"><c:out value="${item.defaultAddress}"/></td>
                <td><input type="submit" name="edit" class="button2 button1 editButton" value="Edit"/></td>
                <td>
                    <a href="<c:url value="/clientsServlet.jsp?removeClient=&clientId=${item.id}"/>">Удалить</a>
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td><input type="submit" name="add" class="add button2 button1" value="Add"/></td>
        </tr>
    </table>
    <form action="clientsServlet.jsp" class="addClientForm">
        <h2>Добавить пользователя</h2>
        <table id="addClientsTable" class="simple-little-table" cellspacing='0'>
            <tr class="row">
                <td class="col">Имя</td>
                <td class="col"><input name="clientName" type="text"></td>
            </tr>
            <tr class="row">
                <td class="col">Адрес</td>
                <td class="col"><input name="clientDefaultAddress" type="text"></td>
            </tr>
        </table>
        <input type="button" name="cancel" class="cancel button2 button1" value="Отмена"/></td>
        <input type="submit" name="addClient" class="saveAdd button2 button1" value="Добавить"/></td>
    </form>
    <form action="categoriesServlet.jsp" class="addCategoryForm">
        <h2>Добавить категорию</h2>
        <table id="addCategoryTable" class="simple-little-table" cellspacing='0'>
            <tr class="row">
                <td class="col">Название</td>
                <td class="col"><input name="categoryName" type="text"></td>
            </tr>
            <tr class="row">
                <td class="col">parentID</td>
                <td class="col"><input name="parentCategoryId" type="text"></td>
            </tr>
        </table>
        <input type="button" name="cancel" class="cancel button2 button1" value="Отмена"/></td>
        <input type="submit" name="addCategory" class="saveAdd button2 button1" value="Добавить"/></td>
    </form>
    <form action="productsServlet.jsp" class="addProductForm">
        <h2>Добавить продукт</h2>
        <table id="addProductTable" class="simple-little-table" cellspacing='0'>
            <tr class="row">
                <td class="col">Название</td>
                <td class="col"><input name="productName" type="text"></td>
            </tr>
            <tr class="row">
                <td class="col">Описание</td>
                <td class="col"><input name="productDescription" type="text"></td>
            </tr>
            <tr class="row">
                <td class="col">Цена</td>
                <td class="col"><input name="productPrice" type="text"></td>
            </tr>
            <tr class="row">
                <td class="col">Количество</td>
                <td class="col"><input name="productCount" type="text"></td>
            </tr>
            <tr class="row">
                <td class="col">Категория</td>
                <td class="col"><input name="productCategoryId" type="text"></td>
            </tr>
            <input type="button" name="cancel" class="cancel button2 button1" value="Отмена"/></td>
            <input type="submit" name="addProduct" class="saveAdd button2 button1" value="Добавить"/></td>
        </table>

    </form>
    <c:if test="${isError==true}">
        <div>
            <p style="color:red;" align="center"><c:out value="${errorMessage}"/></p>
        </div>
    </c:if>
</div>
<script src="js/func.js"></script>
<%@include file="template_end.jsp" %>
