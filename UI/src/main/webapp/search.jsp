<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="env.jsp" %>

<c:set var="title" value="Поиск" scope="page"/>
<c:set var="objStack" value="${[title,'search.jsp']}" scope="page"/>
<c:set var="pathStack" value="${[objStack]}" scope="page"/>
<c:set var="detailsPrefix">/product.jsp?id=</c:set>

<%@include file="template_start.jsp" %>
<div class="row path">
    <ul class="list-inline">
        <li><a href="index.jsp">Главная</a></li>
        <span> > </span>
        <li><a href="search.jsp">Поиск</a></li>
    </ul>
</div>
<!-- Главный Экран- -->
<div class="row">
    <section id="searchBox">
        <hr>
        <h3>Расширенный поиск товаров</h3>
        <form method="POST" action="/searchServlet.jsp" class="findForm">
            <table class="table">
                <thead>
                <tr>
                    <th>Название</th>
                    <th>Категория</th>
                    <th>Цена</th>
                    <th>Количество</th>
                </tr>
                </thead>
                <tbody>
                <td>
                    <div class="col"><input name="nameFilter" type="text" value="${nameValue}"></div>
                </td>
                <td>
                    <div class="col">
                        <select name="categorySelect">
                            <c:if test="${empty categories}">
                                <c:set var="categories" value="${categoryBean.getAll()}"/>
                            </c:if>
                            <c:forEach items="${categories}" var="item">
                                <option name="<c:out value="${item.name}"/>" class="categoryId" value="<c:out value="${item.name}"/>"><c:out value="${item.name}"/></option>
                            </c:forEach>
                        </select>
                    </div>
                </td>
                <td>
                    <input name="minPriceFilter" type="text" class="minPrice" value="${minPriceValue}">
                    <span>-</span>
                    <input name="maxPriceFilter" type="text" class="maxPrice" value="${maxPriceValue}">
                </td>
                <td>
                    <input name="countFilter" type="text" class="countMin" value="${countValue}">
                </tbody>
            </table>
            <div class="row" align="right">
                <input name="findProductWide" value="Поиск" type="submit" class="btn btn-primary findButton">
            </div>
        </form>
        <hr>
        <c:if test='${products.isEmpty()==false}'>
        <h3>Результат поиска</h3>
        <div class="row">
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
                                    <button type=submit name=buy>&#x1F6D2;</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        </c:if>
</div>
</section>
</div>
</section>
<%@include file="template_end.jsp" %>