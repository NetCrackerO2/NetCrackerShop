<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="env.jsp" %>

<%@include file="reqauth.jsp" %>

<c:set var="title" value="Orders" scope="page"/>
<c:set var="orders" value="${orderBean.getByClientId(clientBean.getClientInfo().id)}"/>

<%@include file="template_start.jsp" %>
<div class="main">
    <link rel="stylesheet" type="text/css" href="/css/cart.css">
    <table class="simple-little-table" cellspacing='0'>
        <tbody>
        <tr>
            <td>ID</td>
            <td>Адрес</td>
            <td>Дата</td>
        </tr>
        <c:forEach var="order" items="${orderBean.getByClientId(clientBean.getClientInfo().id)}">
            <tr>
                <td><c:out value="${order.id}"/></td>
                <td><c:out value="${order.addres}"/></td>
                <td><c:out value="${order.date}"/></td>
            </tr>

            <tr>
                <td></td>
                <td></td>
                <td></td>
                <td>Товар</td>
                <td>Количество</td>
                <td>Стоимость</td>
            </tr>
            <c:forEach var="productInOrder" items="${productBean.getInOrder(order.id)}">
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td><c:out value="${productInOrder.name}"/></td>
                    <td><c:out value="${productInOrder.shoppingCount}"/></td>
                    <td><c:out value="${productInOrder.shoppingPrice}"/></td>
                </tr>
            </c:forEach>
        </c:forEach>
        </tbody>
    </table>


    <%@include file="template_end.jsp" %>
