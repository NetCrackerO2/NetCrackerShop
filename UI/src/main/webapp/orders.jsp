<%@ page import="beans.ProductBean" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="env.jsp" %>

<%@include file="reqauth.jsp" %>

<c:set var="title" value="Заказы" scope="page"/>
<c:set var="objStack" value="${[title,'orders.jsp']}" scope="page"/>
<c:set var="pathStack" value="${[objStack]}" scope="page"/>
<c:set var="orders" value="${orderBean.getByClientId(clientBean.getClientInfo().id)}"/>

<%@include file="template_start.jsp" %>
<div class="row path">
    <ul class="list-inline">
        <li><a href="index.jsp">Главная</a></li>
        <span> > </span>
        <li><a href="user_profile.jsp">Профиль</a></li>
        <span> > </span>
        <li><a href="orders.jsp">Заказы</a></li>
    </ul>
</div>
<!-- Главный Экран- -->
<div class="row">
    <div class="col-md-6 col-md-offset-3">
        <table class="table table-striped table-bordered" cellspacing='0'>
            <thead>
            <tr>
                <th>ID</th>
                <th>Адрес</th>
                <th>Дата</th>
                <th></th>
            </tr>
            </thead>
            <tbody>

            <c:forEach var="order" items="${orderBean.getByClientId(clientBean.getClientInfo().id)}">
                <form method="get" action="/cart.jsp">
                    <input name="orderId" value="${order.id}" type="hidden">
                    <tr>
                        <td><c:out value="${order.id}"/></td>
                        <td><c:out value="${order.address}"/></td>
                        <td><c:out value="${order.date}"/></td>
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

<!-- МОДАЛЬНЫЕ ОКНА -->
<!-- Добавить категорию -->
<div ng-app="app" ng-controller="Ctrl" class="modal fade" id="order_info_Modal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2>Добавить категорию</h2>
            </div>
            <div class="modal-body">
                <div class="row">
                    <form action="categoriesServlet.jsp" class="addCategoryForm">
                        <table id="addCategoryTable" class="table text-center" cellspacing='0'>
                            <thead>
                            <tr>
                                <th>Товар</th>
                                <th>Количество</th>
                                <th>Стоимость</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:out value="{{product}}"/>
                            <%--<c:forEach var="productInOrder"--%>
                            <%--items="${productBean.getInOrder(Integer.parseInt('${i}'))}">--%>
                            <%--<tr>--%>
                            <%--<td><c:out value="${productInOrder.name}"/></td>--%>
                            <%--<td><c:out value="${productInOrder.shoppingCount}"/></td>--%>
                            <%--<td><c:out value="${productInOrdser.shoppingPrice}"/></td>--%>
                            <%--</tr>--%>
                            <%--</c:forEach>--%>
                            </tbody>
                        </table>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>