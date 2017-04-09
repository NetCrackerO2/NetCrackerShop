<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="env.jsp" %>

<%@include file="reqauth.jsp" %>

<c:set var="title" value="Admin panel" scope="page"/>
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
            <c:forEach items="${productBean.getAll()}" var="item">
                <tr>
                    <td><c:out value="${item.id}"/></td>
                    <td><c:out value="${item.name}"/></td>
                    <td><c:out value="${item.count}"/></td>
                    <td>$<c:out value="${item.price}"/></td>
                    <td><input type="submit" name="del" class="button2 button1" value="Edit"/></td>
                    <td><input type="submit" name="del" class="delete button2 button1" value="Del"/></td>
                </tr>
            </c:forEach>
        </table>
        <div>
            <input type="submit" name="submit" class="button1" value="Сохранить"/>
        </div>
    </form>
    <form id="categoryForm" name="categoryForm" action="" method="">
        <table id="categoryTable" class="simple-little-table" cellspacing='0'>
            <tr>
                <th>ID</th>
                <th>Наименование</th>
            </tr><!-- Table Header -->
            <c:forEach items="${categoryBean.getAll()}" var="item">
                <tr>
                    <td><c:out value="${item.id}"/></td>
                    <td><c:out value="${item.name}"/></td>
                    <td><input type="submit" name="del" class="button2 button1" value="Edit"/></td>
                    <td><input type="submit" name="del" class="delete button2 button1" value="Del"/></td>
                </tr>
            </c:forEach>
        </table>
        <div>
            <input type="submit" name="submit" class="button1" value="Сохранить"/>
        </div>
    </form>
</div>
<script src="../js/func.js"></script>
<%@include file="template_end.jsp" %>
