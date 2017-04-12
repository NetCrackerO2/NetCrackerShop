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
                <td><input type="submit" name="del" class="button2 button1 editButton" value="Edit"/></td>
                <td><input type="submit" name="del" class="delete button2 button1" value="Del"/></td>
            </tr>
        </c:forEach>
        <tr>
            <td><input type="submit" name="add" class="add button2 button1" value="Add"/></td>
        </tr>

    </table>
    </form>
    <table id="categoryTable" class="simple-little-table" cellspacing='0'>
        <tr>
            <th>ID</th>
            <th>Наименование</th>
        </tr><!-- Table Header -->
        <c:forEach items="${categoryBean.getAll()}" var="item">
            <tr>
                <td class="editable"><c:out value="${item.id}"/></td>
                <td class="editable"><c:out value="${item.name}"/></td>
                <td><input type="submit" name="del" class="button2 button1 editButton" value="Edit"/></td>
                <td><input type="submit" name="del" class="delete button2 button1" value="Del"/></td>
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
        <c:forEach items="${clientBean.getAll()}" var="client">
            <tr>
                <td class="editable"><c:out value="${client.id}"/></td>
                <td class="editable"><c:out value="${client.name}"/></td>
                <td class="editable"><c:out value="${client.defaultAddress}"/></td>
                <td><input type="submit" name="del" class="button2 button1 editButton" value="Edit"/></td>
                <td><input type="submit" name="del" class="delete button2 button1" value="Del"/></td>
            </tr>
        </c:forEach>
        <tr>
            <td><input type="submit" name="add" class="add button2 button1" value="Add"/></td>
        </tr>
    </table>
    <form action="AddClientServlet" class="addClientForm">
        <h2>Добавить пользователя</h2>
        <table id="addClientsTable" class="simple-little-table" cellspacing='0'>
            <tr class="row">
                <td class="col">ID</td>
                <td class="col"><input name="idClientNew" type="text"></td>
            </tr>
            <tr class="row">
                <td class="col">Имя</td>
                <td class="col"><input name="nameClientNew" type="text"></td>
            </tr>
            <tr class="row">
                <td class="col">Адрес</td>
                <td class="col"><input name="addressClientNew" type="text"></td>
            </tr>
        </table>
        <input type="button" name="cancel" class="cancel button2 button1" value="Отмена"/></td>
        <input type="submit" name="add" class="saveAdd button2 button1" value="Добавить"/></td>
    </form>
    <form action="AddCategoryServlet" class="addCategoryForm">
        <h2>Добавить категорию</h2>
        <table id="addCategoryTable" class="simple-little-table" cellspacing='0'>
            <tr class="row">
                <td class="col">ID</td>
                <td class="col"><input name="idCategoryNew" type="text"></td>
            </tr>
            <tr class="row">
                <td class="col">Название</td>
                <td class="col"><input name="nameCategoryNew" type="text"></td>
            </tr>
        </table>
        <input type="button" name="cancel" class="cancel button2 button1" value="Отмена"/></td>
        <input type="submit" name="add" class="saveAdd button2 button1" value="Добавить"/></td>
    </form>
    <form action="AddProductServlet" class="addProductForm">
        <h2>Добавить продукт</h2>
        <table id="addProductTable" class="simple-little-table" cellspacing='0'>
            <tr class="row">
                <td class="col">ID</td>
                <td class="col"><input name="idProductNew" type="text"></td>
            </tr>
            <tr class="row">
                <td class="col">Название</td>
                <td class="col"><input name="nameProductNew" type="text"></td>
            </tr>
            <tr class="row">
                <td class="col">Цена</td>
                <td class="col"><input name="priceProductNew" type="text"></td>
            </tr>
            <tr class="row">
                <td class="col">Количество</td>
                <td class="col"><input name="countProductNew" type="text"></td>
            </tr>
            <tr class="row">
                <td class="col">Категория</td>
                <td class="col"><input name="category_id" type="text"></td>
            </tr>
            <input type="button" name="cancel" class="cancel button2 button1" value="Отмена"/></td>
            <input type="submit" name="add" class="saveAdd button2 button1" value="Добавить"/></td>
        </table>

    </form>

</div>
<script src="js/func.js"></script>
<%@include file="template_end.jsp" %>
