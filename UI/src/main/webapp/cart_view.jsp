<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="env.jsp" %>

<%@include file="reqauth.jsp" %>

<c:set var="title" value="Корзина" scope="page"/>
<c:set var="objStack" value="${[title,'cart_view.jsp']}" scope="page"/>
<c:set var="pathStack" value="${[objStack]}" scope="page"/>
<c:set var="detailsPrefix">/product.jsp?id=</c:set>
<c:set var="isEnough" value="true"/>

<%@include file="template_start.jsp" %>
<c:set var="crumbs">
    <a href="index.jsp">Главная</a>,
    <a href="cart_view.jsp">Корзина</a>
</c:set>
<jsp:include page="menu.jsp">
    <jsp:param name="crumbs" value="${crumbs}"/>
    <jsp:param name="title" value="${title}"/>
</jsp:include>
</nav>

<!-- Главный Экран- -->
<div class="container">
    <div class="row">
        <hr>
        <div class="col-md-6 col-md-offset-3">
            <form id="cartForm" name="orderButton" action="" method="post">
                <table class="table" cellspacing='0'>
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Наименование</th>
                        <th>Описание</th>
                        <th>Категория</th>
                        <th>Количество</th>
                        <th>Цена</th>
                        <th>Стоимость</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:set var="sum" value="0"/>
                    <c:forEach items="${cartBean.cart}" var="cartItem">
                        <c:set var="item" value="${productBean.get(cartItem.productId)}"/>
                        <c:set var="sum" value="${sum + item.price * cartItem.count}"/>
                        <tr>
                            <td><c:out value="${item.id}"/></td>
                            <td><a href="<c:url value='${detailsPrefix}${item.id}'/>"><c:out
                                    value="${item.name}"/></a></td>
                            <td><c:out value="${item.description}"/></td>
                            <td><c:out value="${item.getCategory().getName()}"/></td>
                            <td>
                                <c:out value="${cartItem.count}"/>
                                <c:if test="${cartItem.count > item.count}">
                                    <c:set var="isEnough" value="false"/>
                                    <font color="red">(на складе <c:out value="${item.count}"/>)</font>
                                </c:if>
                            </td>
                            <td><c:out value="${item.price}"/>$</td>
                            <td>
                                <c:out value="${item.price * cartItem.count}"/>$
                            </td>
                            <td>
                                <a href="<c:url value='/cart.jsp?remove=&id=${item.id}'/>"
                                   class="btn btn-primary">Удалить</a>
                            </td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td>Итого:</td>
                        <td><c:out value="${sum}"/>$</td>
                        <td>
                            <c:if test="${isEnough == true}">
                                <div>
                                    <input type="submit" name="createOrder" class="btn btn-primary"
                                           value="Оформить заказ" class="button"/>
                                </div>
                            </c:if>
                        </td>
                    </tr>
                    <c:if test="${isError==true}">
                        <tr>
                            <td colspan="6"><p style="color:red;" align="center"><c:out
                                    value="${errorMessage}"/></p></td>
                        </tr>
                    </c:if>
                    </tbody>
                </table>
                <p>Адрес доставки товаров:</p>
                <input name="address" type="text" class="input address" placeholder="г. Припять, энергоблок №4"/>
            </form>
        </div>
    </div>
</div>
</div>
</section>
<%@include file="template_end.jsp" %>