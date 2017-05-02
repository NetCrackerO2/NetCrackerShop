<%@ page import="beans.ProductBean" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="env.jsp" %>

<%@include file="reqauth.jsp" %>

<c:set var="title" value="Заказ" scope="page"/>
<c:set var="order" value="${productBean.getInOrder(Integer.parseInt(orderId))}"/>


<%@include file="template_start.jsp" %>
<div class="row path">
    <ul class="list-inline">
        <li><a href="index.jsp">Главная</a></li>
        <span> > </span>
        <li><a href="user_profile.jsp">Профиль</a></li>
        <span> > </span>
        <li><a href="orders.jsp">Заказы</a></li>
        <span> > </span>
        <li><a href="orders.jsp">Заказ №${orderId}</a></li>
    </ul>
</div>
<!-- Главный Экран- -->
<div class="row">
    <div class="col-md-6 col-md-offset-3">
        <table id="productsInOrder" class="table table-striped table-bordered text-center" cellspacing='0'>
            <thead>
            <tr>
                <th>Товар</th>
                <th>Количество</th>
                <th>Стоимость</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="productInOrder" items="${order}">
                <tr>
                    <td><c:out value="${productInOrder.name}"/></td>
                    <td><c:out value="${productInOrder.shoppingCount}"/></td>
                    <td><c:out value="${productInOrder.shoppingPrice}"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</div>
</section>
<%@include file="template_end.jsp" %>
