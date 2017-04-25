<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="env.jsp" %>

<fmt:parseNumber var="categoryId" integerOnly="true" type="number" value="${param.category}"/>
<fmt:parseNumber var="productId" integerOnly="true" type="number" value="${param.id}"/>
<c:set var="product" value="${productBean.get(productId)}"/>
<%--<c:set var="pathStack" value="${['Categories', product.getCategoryByCategoryId().getName(), product.getName()]}"--%>
<%--scope="page"/>--%>
<c:set var="title" value="${product.name}" scope="page"/>
<c:set var="objStack" value="${[title,'product.jsp',product.getCategoryByCategoryId().getName()]}" scope="page"/>
<c:set var="pathStack" value="${[objStack]}" scope="page"/>
<c:set var="detailsPrefix">/products.jsp?category=</c:set>

<%@include file="template_start.jsp" %>
<div class="row path">
    <ul class="list-inline">
        <li><a href="index.jsp">Главная</a></li>
        <span> > </span>
        <li><a href="categories.jsp">Категории</a></li>
        <span> > </span>
        <li><a href="#">${product.getCategoryByCategoryId().getName()}</a></li>
        <span> > </span>
        <li><a href="#">${product.getName()}</a></li>
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
            <tr>
                <td>
                    <c:out value="${product.name}"/></td>
                <td>
                    <desc><c:out value="${product.description}"/></desc>
                </td>
                <td>
                    <price>$<c:out value="${product.price}"/></price>
                </td>
                <td>
                    <form method="POST" action="/cart.jsp">
                        <input type="hidden" name="id" value="<c:out value='${product.id}'/>"/>
                        <input type="number" name="count" min="1" max="<c:out value='${product.count}'/>"
                               value="1"/>
                        <button type=submit name=buy>&#x1F6D2;</button>
                    </form>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
</div>
</section>
<%@include file="template_end.jsp" %>