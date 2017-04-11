<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="env.jsp" %>

<%@include file="reqauth.jsp" %>

<c:set var="title" value="Shopping Cart" scope="page"/>
<c:set var="pathStack" value="${['Shopping Cart']}" scope="page"/>
<c:set var="detailsPrefix">/product.jsp?id=</c:set>

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
    <div class="text3">Что-то будет...</div>
</aside>
<div class="main">
    <link rel="stylesheet" type="text/css" href="/css/cart.css">
    <form id="cartForm" name="orderButton" action="" method="post">
        <table class="simple-little-table" cellspacing='0'>
            <tr>
                <th>ID</th>
                <th>Наименование</th>
                <th>Описание</th>
                <th>Количество</th>
                <th>Цена</th>
            </tr>
            <tbody>
            <c:set var="sum" value="0"/>
            <c:forEach items="${cartBean.cart}" var="cartItem">
                <c:set var="item" value="${productBean.get(cartItem.productId)}"/>
                <c:set var="sum" value="${sum + item.price * cartItem.count}"/>
                <tr>
                    <td><c:out value="${item.id}"/></td>
                    <td><a href="<c:url value="${detailsPrefix}${item.id}"/>"><c:out value="${item.name}"/></a></td>
                    <td><c:out value="${item.description}"/></td>
                    <td><c:out value="${cartItem.count}"/></td>
                    <td><c:out value="${item.price}"/>$</td>
                    <td><a href="<c:url value="/cart.jsp?remove=&id=${item.id}"/>">Удалить</a></td>
                </tr>
            </c:forEach>
            <tr>
                <td></td>
                <td></td>
                <td></td>
                <td>Итого:</td>
                <td><c:out value="${sum}"/>$</td>
                <td>
                    <div>
                        <input type="submit" name="createOrder" class="button1" value="Оформить заказ" class="button"/>
                    </div>
                </td>
            </tr>
            <c:if test="${isError==true}">
                <tr>
                    <td colspan="6"><p style="color:red;" align="center"><c:out value="${errorMessage}"/></p></td>
                </tr>
            </c:if>
            </tbody>
        </table>
        <p>Адрес доставки товаров:</p>
        <input name="addres" type="text" class="input addres" placeholder="г. Припять, энергоблок №4"/>
    </form>
    <%@include file="template_end.jsp" %>
