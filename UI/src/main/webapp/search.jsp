<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="env.jsp" %>

<c:set var="pathStack" value="${['Search']}" scope="page"/>
<c:set var="title" value="Search" scope="page"/>

<%@include file="template_start.jsp" %>
<!-- Главный Экран- -->
<section id="content">
    <div class="container">
        <div class="row">
            <div class="col-md-6 col-md-offset-3">
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
            </div>
        </div>
    </div>
</section>
<%@include file="template_end.jsp" %>