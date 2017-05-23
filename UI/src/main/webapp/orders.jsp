<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="env.jsp" %>

<%@include file="reqauth.jsp" %>

<c:set var="title" value="Заказы" scope="page"/>
<c:set var="orders" value="${orderBean.getByClientId(clientInfo.id)}"/>

<%@include file="template_start.jsp" %>
<c:set var="crumbs">
    <a href="index.jsp">Главная</a>,
    <a href="user_profile.jsp">Личный кабинет</a>,
    <a href="orders.jsp">Заказы</a>
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
        <table class="table table-striped table-bordered" cellspacing='0'>
            <thead>
            <tr>
                <th>ID</th>
                <th>Дата</th>
                <th>Адрес</th>
                <th></th>
            </tr>
            </thead>
            <tbody>

            <c:forEach var="order" items="${orderBean.getByClientId(clientInfo.id)}">
                <form method="get" action="/order.jsp">
                    <input name="orderId" value="${order.id}" type="hidden">
                    <tr>
                        <td><c:out value="${order.id}"/></td>
                        <td><c:out value="${order.date}"/></td>
                        <td><c:out value="${order.address}"/></td>
                        <td>
                            <input id="orderButton" value="Подробнее" name="orderInfoButton" type="submit"
                                   class="btn btn-primary">
                        </td>
                    </tr>
                </form>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</div>
</section>
<%@include file="template_end.jsp" %>