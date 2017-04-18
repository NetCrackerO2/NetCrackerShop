<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="env.jsp" %>

<fmt:parseNumber var="productId" integerOnly="true" type="number" value="${param.id}"/>
<c:set var="product" value="${productBean.get(productId)}"/>
<c:set var="pathStack" value="${['Categories', product.getCategoryByCategoryId().getName(), product.getName()]}"
       scope="page"/>
<c:set var="title" value="${product.name}" scope="page"/>

<%@include file="template_start.jsp" %>
<aside>
    <div id="categoryName" class="text1"><c:out value="${title}"/></div>
    <div class="text3">Критерии поиска</div>
</aside>
<div class="main">
    <form action="SearchServlet" class="findForm">
        <div class="wrapper">
            <div class="table filterTable">
                <div class="row">
                    <div class="col text3">Название</div>
                    <div class="col"><input name="nameFilter" value="${findInput}" type="text"></div>
                </div>
                <div class="row">
                    <div class="col"><span class="text3">Категория</span></div>
                    <div class="col"><input name="categoryFilter" type="text" class="categoryId"></div>
                    <div class="col"><span class="text3">Цена</span></div>
                    <div class="col"><input name="minPriceFilter" type="text" class="minPrice">
                        <span>-</span>
                        <input name="maxPriceFilter" type="text" class="maxPrice">
                    </div>
                    <div class="filterCount">
                        <div class="col"><span class="text3">Кол-во на складе:</span></div>
                        <div class="col"><input name="countFilter" type="text" class="countMin"></div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col"><input name="findProductWide" type="submit" class="findButton"></div>
        </div>
    </form>
    <c:forEach items="${products}" var="item">
    <article class="z1">
        <a href="<c:url value="${detailsPrefix}${item.id}"/>">
            <h2><c:out value="${item.name}"/></h2>
        </a>
        <desc><c:out value="${item.description}"/></desc>
        <price>$<c:out value="${item.price}"/></price>
        <count><c:out value="${item.count}"/> шт.</count>
    </article>
    </c:forEach>
<%@include file="template_end.jsp" %>