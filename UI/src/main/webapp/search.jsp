<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="env.jsp" %>

<c:set var="title" value="Поиск" scope="page"/>
<c:set var="objStack" value="${[title,'search.jsp']}" scope="page"/>
<c:set var="pathStack" value="${[objStack]}" scope="page"/>
<c:set var="detailsPrefix">/product.jsp?id=</c:set>

<%@include file="template_start.jsp" %>
<c:set var="crumbs">
    <a href="index.jsp">Главная</a>,
    <a href="search.jsp">Поиск</a>
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
        <div class="col-md-12">
            <section id="searchBox">
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
                                    <option name="<Все>" class="categoryId"
                                            value="<Все>"
                                            <c:if test="${item.name == categorySelectValue}">
                                                selected
                                            </c:if>
                                    >
                                        <c:out value="<Все>"/>
                                    </option>
                                    <c:if test="${empty categories}">
                                        <c:set var="categories" value="${categoryBean.getAll()}"/>
                                    </c:if>
                                    <c:forEach items="${categories}" var="item">
                                        <option name="<c:out value="${item.name}"/>" class="categoryId"
                                                value="<c:out value="${item.name}"/>"
                                                <c:if test="${item.name == categorySelectValue}">
                                                    selected
                                                </c:if>
                                        >
                                            <c:out value="${item.name}"/>
                                        </option>
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
                    <div class="col-md-10">
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
                                            <input type="number" name="count" min="1"
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
                </c:if>
        </div>
        </section>
    </div>
</div>
</div>
</section>
<%@include file="template_end.jsp" %>