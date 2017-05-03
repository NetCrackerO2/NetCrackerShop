<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="env.jsp" %>

<%@include file="reqauth.jsp" %>

<c:set var="title" value="Admin panel" scope="page"/>
<c:set var="pathStack" value="${['Admin']}" scope="page"/>

<%@include file="template_start.jsp" %>
<div class="container">
    <div class="row">
        <ul class="breadCrumbs list-inline">
            <li><a href="index.jsp">Главная</a></li>
            <span> > </span>
            <li><a href="admin_view.jsp">Админка-товары</a></li>
        </ul>
        <div class="text-right">
            <form method="POST" action="/searchServlet.jsp" class="search">
                <table>
                    <tr>
                        <td><input type="search" name="nameFilter" placeholder="поиск" class="input"/></td>
                        <td><input name="findProductWide" value="" type="submit" class="findButton"/></td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>
<div class="row">
    <ul class="list-inline navigate">
        <li><a href="categories.jsp">Категории</a></li>
        <li><a href="search.jsp">Поиск</a></li>
        <c:if test="${clientInfo.loggedIn}">
            <li><a href="user_profile.jsp">Личный кабинет</a></li>
            <li><a href="admin_view.jsp">Админка</a></li>
        </c:if>
    </ul>
</div>
</nav>
<!-- Главный Экран- -->
<div class="row">
    <div class="col-md-6 col-md-offset-2">
        <table id="productTable" class="table table-striped table-bordered" cellspacing='0'>
            <thead>
            <tr>
                <td>
                    <a href="#add_product_Modal" class="btn btn-primary" data-toggle="modal">Добавить</a>
                </td>
            </tr>
            <tr>
                <th>ID</th>
                <th>Наименование</th>
                <th>Количество</th>
                <th>Цена</th>
                <th colspan="2">Редактирование</th>
            </tr><!-- Table Header -->
            </thead>
            <tbody>
            <c:forEach items="${productBean.getAll()}" var="item">
                <tr>
                    <td class="id"  name="idProduct">
                        <c:out value="${item.id}"/>
                    </td>
                    <td  name="nameProduct" class="name editable">
                        <c:out value="${item.name}"/>
                    </td>
                    <td  name="countProduct" class="count editable">
                        <c:out value="${item.count}"/>
                    </td>
                    <td  name="priceProduct" class="price editable">
                        <c:out value="${item.price}"/>
                    </td>
                    <td>
                        <input type="submit" name="edit" class="btn btn-primary editProductButton" value="Изменить"/>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${productBean.canRemove(item)}">
                                <a href="<c:url value="/productsServlet.jsp?removeProduct=&productId=${item.id}"/>"
                                   class="btn btn-primary">Удалить</a>
                            </c:when>
                            <c:otherwise>
                                <a class="btn">Удалить</a>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
    </div>
    <div class="col-md-2 col-md-offset-1">
        <ul class="list-group submenu">
            <li class="list-group-item"><a href="admin_view.jsp">Работа с товаром</a></li>
            <li class="list-group-item"><a href="admin_category.jsp">Работа с категориями</a></li>
            <li class="list-group-item"><a href="admin_clients.jsp">Работа с клиентами</a></li>
        </ul>
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
                                <td class="col"><input name="productName" type="text"></td>
                            </tr>
                            <tr class="row">
                                <td class="col">Описание</td>
                                <td class="col"><input name="productDescription" type="text"></td>
                            </tr>
                            <tr class="row">
                                <td class="col">Цена</td>
                                <td class="col"><input name="productPrice" type="text"></td>
                            </tr>
                            <tr class="row">
                                <td class="col">Количество</td>
                                <td class="col"><input name="productCount" type="text"></td>
                            </tr>
                            <tr class="row">
                                <td class="col">Категория</td>
                                <td class="col">
                                    <select name="categorySelect">
                                        <c:set var="categories" value="${categoryBean.getAll()}"/>
                                        <c:forEach items="${categories}" var="item">
                                            <option name="<c:out value="${item.name}"/>" value="<c:out value="${item.name}"/>"><c:out value="${item.name}"/></option>
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
