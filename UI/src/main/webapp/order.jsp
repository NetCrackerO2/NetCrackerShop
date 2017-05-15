<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="env.jsp" %>

<%@include file="reqauth.jsp" %>

<c:set var="title" value="Заказ №${orderId}" scope="page"/>
<c:set var="order" value="${productBean.getInOrder(Integer.parseInt(orderId))}"/>


<%@include file="template_start.jsp" %>
<c:set var="crumbs">
    <a href="index.jsp">Главная</a>,
    <a href="user_profile.jsp">Личный кабинет</a>,
    <a href="orders.jsp">Заказы</a>,
    <a href="orders.jsp">Заказ №${orderId}</a>
</c:set>
<jsp:include page="menu.jsp">
    <jsp:param name="crumbs" value="${crumbs}"/>
    <jsp:param name="title" value="${title}"/>
</jsp:include>
</nav>

<!-- Главный Экран- -->
<div class="row">
    <hr>
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
