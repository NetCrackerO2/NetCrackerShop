<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="env.jsp" %>

<%@include file="reqauth.jsp" %>
<%@include file="reqadmin.jsp" %>

<c:set var="title" value="Админ-панель: Товары" scope="page"/>

<%@include file="template_start.jsp" %>
<c:set var="crumbs">
    <a href="index.jsp">Главная</a>,
    <a href="productsServlet.jsp">Админ-панель: Товары</a>
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
        <div class="col-md-8">
            <table id="productTable" class="table table-striped table-bordered" cellspacing='0'>
                <thead>
                <tr>
                    <td>
                        <a href="#add_product_Modal" class="btn btn-primary" data-toggle="modal">Добавить</a>
                    </td>
                </tr>
                <tr>
                    <th>ID</th>
                    <th>Название</th>
                    <th>Категория</th>
                    <th>Описание</th>
                    <th>Количество</th>
                    <th>Цена (в $)</th>
                    <th colspan="3">Редактирование</th>
                </tr><!-- Table Header -->
                </thead>
                <tbody>
                <c:forEach items="${productBean.getAll()}" var="item">
                    <tr <c:out value="${item.disabled?'class=disabled':''}"/>>
                        <td class="id" name="idProduct">
                            <c:out value="${item.id}"/>
                        </td>
                        <td name="nameProduct" class="name contenteditable editable">
                            <c:out value="${item.name}"/>
                        </td>
                        <td class="editable">
                            <select class="category" name="categorySelect" disabled="true">
                                <c:set var="categories" value="${categoryBean.getAll()}"/>
                                <c:forEach items="${categories}" var="cat">
                                    <option name="<c:out value="${cat.name}"/>"
                                            value="<c:out value="${cat.name}"/>"
                                            <c:if test="${cat.name == item.getCategory().getName() }">
                                                selected
                                            </c:if>
                                    >
                                        <c:out value="${cat.name}"/>
                                    </option>
                                </c:forEach>
                            </select>
                        </td>
                        <td name="descriptionProduct" class="description contenteditable editable">
                            <c:out value="${item.description}"/>
                        </td>
                        <td name="countProduct" class="count contenteditable editable">
                            <c:out value="${item.count}"/>
                        </td>
                        <td name="priceProduct" class="price contenteditable editable">
                            <c:out value="${item.price}"/>
                        </td>
                        <td>
                            <input type="submit" name="edit" class="btn btn-primary editProductButton"
                                   value="Изменить"/>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${item.disabled}">
                                    <a href="<c:url value="/productsServlet.jsp?enableProduct=&productId=${item.id}"/>"
                                       class="btn btn-primary">Включить</a>
                                </c:when>
                                <c:otherwise>
                                    <a href="<c:url value="/productsServlet.jsp?disableProduct=&productId=${item.id}"/>"
                                       class="btn btn-primary">Отключить</a>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${productBean.canRemove(item)}">
                                    <a href="<c:url value="/productsServlet.jsp?removeProduct=&productId=${item.id}"/>"
                                       class="btn btn-primary">Удалить</a>
                                </c:when>
                                <c:otherwise>
                                    <span class="reserve">Товар содержится в заказах</span>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="col-md-2 col-md-offset-2">
            <ul class="list-group submenu">
                <li class="list-group-item"><a href="productsServlet.jsp">Работа с товаром</a></li>
                <li class="list-group-item"><a href="categoriesServlet.jsp">Работа с категориями</a></li>
                <li class="list-group-item"><a href="clientsServlet.jsp">Работа с клиентами</a></li>
                <li class="list-group-item"><a href="admin_exp_imp.jsp">Экспорт/Импорт</a></li>
            </ul>
        </div>
    </div>
</div>
</div>
</section>


<!-- МОДАЛЬНЫЕ ОКНА -->

<!-- Добавить продукт -->
<div class="modal fade" id="add_product_Modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2>Добавить продукт</h2>
            </div>
            <div class="modal-body">
                <div class="row">
                    <form action="productsServlet.jsp" class="addProductForm">
                        <table id="addProductTable" class="table text-center" cellspacing='0'>
                            <tr class="row">
                                <td class="col">Название</td>
                                <td class="col"><input name="productName" type="text" value="${productNameValue}"></td>
                            </tr>
                            <tr class="row">
                                <td class="col">Описание</td>
                                <td class="col"><input name="productDescription" type="text"
                                                       value="${productDescriptionValue}"></td>
                            </tr>
                            <tr class="row">
                                <td class="col">Цена</td>
                                <td class="col"><input name="productPrice" type="text" value="${productPriceValue}">
                                </td>
                            </tr>
                            <tr class="row">
                                <td class="col">Количество</td>
                                <td class="col"><input name="productCount" type="text" value="${productCountValue}">
                                </td>
                            </tr>
                            <tr class="row">
                                <td class="col">Категория</td>
                                <td class="col">
                                    <select name="categorySelect">
                                        <c:set var="categories" value="${categoryBean.getAll()}"/>
                                        <c:forEach items="${categories}" var="item">
                                            <option name="<c:out value="${item.name}"/>"
                                                    value="<c:out value="${item.name}"/>"
                                                    <c:if test="${item.name == categorySelectValue}">
                                                        selected
                                                    </c:if>
                                            >
                                                <c:out value="${item.name}"/>
                                            </option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr class="row">
                                <td>
                                    <input type="submit" name="addProduct" class="saveAdd btn btn-primary"
                                           value="Добавить"/>
                                </td>
                                <td>
                                    <input type="button" name="cancel" class="cancel btn btn-primary"
                                           data-dismiss="modal" value="Отмена"/>
                                </td>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="../js/func.js"></script>
<%@include file="template_end.jsp" %>
