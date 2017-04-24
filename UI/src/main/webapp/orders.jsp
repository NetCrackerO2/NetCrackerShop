<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="env.jsp" %>

<%@include file="reqauth.jsp" %>

<c:set var="title" value="Orders" scope="page"/>
<c:set var="orders" value="${orderBean.getByClientId(clientBean.getClientInfo().id)}"/>

<%@include file="template_start.jsp" %>
<!-- Главный Экран- -->
<section id="content">
    <div class="container">
        <div class="row">
            <div class="col-md-6 col-md-offset-3">
                <table class="table" cellspacing='0'>
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Адрес</th>
                        <th>Дата</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="order" items="${orderBean.getByClientId(clientBean.getClientInfo().id)}">
                        <tr>
                            <td><c:out value="${order.id}"/></td>
                            <td><c:out value="${order.address}"/></td>
                            <td><c:out value="${order.date}"/></td>
                        </tr>

                        <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                            <th>Товар</th>
                            <th>Количество</th>
                            <th>Стоимость</th>
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
            </div>
        </div>
    </div>
</section>
<%@include file="template_end.jsp" %>