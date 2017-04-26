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
<div class="row path">
    <ul class="list-inline">
        <li><a href="index.jsp">Главная</a></li>
        <span> > </span>
        <li><a href="categories.jsp">Категории</a></li>
        <span> > </span>
        <li><a href="<c:url value="${detailsPrefix}${categoryId}"/>">${title}</a></li>
    </ul>
</div>
<!-- Главный Экран- -->
<div class="row">
    <div class="col-md-8 col-md-offset-3">

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
                            <button type=submit name=buy>&#x1F6D2;</button>
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