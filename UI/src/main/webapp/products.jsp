<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="env.jsp" %>

<c:choose>
    <c:when test='${param.category != null}'>
        <fmt:parseNumber var="categoryId" integerOnly="true" type="number" value="${param.category}"/>
        <c:set var="products" value="${productBean.getByCategory(categoryId)}"/>
        <c:set var="urlPostfix">?category=${categoryId}</c:set>

        <c:choose>
            <c:when test="${categoryBean.get(categoryId) == null}">
                <c:set var="title" value="Неизвестно" scope="page"/>
                <c:set var="urlPostfix" value=""/>
            </c:when>
            <c:otherwise>
                <c:set var="title" value="${categoryBean.get(categoryId).name}" scope="page"/>
            </c:otherwise>
        </c:choose>


    </c:when>
    <c:otherwise>
        <c:set var="products" value="${productBean.getAll()}"/>
        <c:set var="title" value="Товары" scope="page"/>
    </c:otherwise>
</c:choose>
<c:set var="detailsPrefix">/product.jsp?id=</c:set>

<%@include file="template_start.jsp" %>
<c:set var="crumbs">
    <a href="index.jsp">Главная</a>,
    <a href="categories.jsp">Категории</a>,
    <a href="<c:url value="/products.jsp${urlPostfix}"/>">${title}</a>
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
        <div class="col-md-8 col-md-offset-2">
            <table id="productsTable" class="table table-striped table-bordered" cellspacing='0'>
                <thead>
                <tr>
                    <th>Название</th>
                    <th>Описание</th>
                    <th>Цена</th>
                    <th>Количество</th>
                </tr><!-- Table Header -->
                </thead>
                <tbody>
                <c:forEach items="${products}" var="item">
                    <tr>
                        <td>
                            <c:out value="${item.name}"/>
                        </td>
                        <td>
                            <desc><c:out value="${item.description}"/></desc>
                        </td>
                        <td>
                            <price>$<c:out value="${item.price}"/></price>
                        </td>
                        <td>
                            <count><c:out value="${item.count}"/> шт.</count>
                        </td>
                        <td>
                            <form method="POST" action="/cart.jsp">
                                <input type="hidden" name="id" value="<c:out value='${item.id}'/>"/>
                                <input class="cntProduct" type="number" name="count" min="1"
                                       max="<c:out value='${item.count}'/>"
                                       value="1"/>
                                <button type=submit name=buy class="btn btn-primary"><img class="icon"
                                                                                          src="../image/cart.png">
                                </button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<%@include file="template_end.jsp" %>