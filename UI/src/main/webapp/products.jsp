<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="env.jsp" %>

<c:choose>
    <c:when test='${param.category != null}'>
        <fmt:parseNumber var="categoryId" integerOnly="true" type="number" value="${param.category}"/>
        <c:set var="products" value="${productBean.getByCategory(categoryId)}"/>
        <c:set var="title" value="${categoryBean.get(categoryId).name}" scope="page"/>
        <%--<c:set var="pathStack" value="${['Categories', categoryBean.get(categoryId).name]}" scope="page"/>--%>
        <c:set var="objStack" value="${[title,categoryBean.get(categoryId).name,'products.jsp']}" scope="page"/>
        <c:set var="pathStack" value="${[objStack]}" scope="page"/>
    </c:when>
    <c:otherwise>
        <c:set var="products" value="${productBean.getAll()}"/>
        <c:set var="title" value="Products" scope="page"/>
        <c:set var="pathStack" value="${['Products']}" scope="page"/>
    </c:otherwise>
</c:choose>
<c:set var="detailsPrefix">/product.jsp?id=</c:set>

<%@include file="template_start.jsp" %>
<c:set var="crumbs">
    <a href="index.jsp">Главная</a>,
    <a href="categories.jsp">Категории</a>,
    <a href="<c:url value="${detailsPrefix}${categoryId}"/>">${title}</a>
</c:set>
<jsp:include page="menu.jsp">
    <jsp:param name="crumbs" value="${crumbs}"/>
    <jsp:param name="title" value="${title}"/>
</jsp:include>
</nav>

<!-- Главный Экран- -->
<div class="row">
    <hr>
    <div class="col-md-8 col-md-offset-2">
        <table id="clientsTable" class="table table-striped table-bordered" cellspacing='0'>
            <thead>
            <tr>
                <th>Наименование</th>
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
                                <input type="number" name="count" min="1" max="<c:out value='${item.count}'/>"
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
</section>

<%@include file="template_end.jsp" %>