<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="env.jsp" %>

<%@include file="reqauth.jsp" %>
<%@include file="reqadmin.jsp" %>

<c:set var="title" value="Admin panel" scope="page"/>
<c:set var="pathStack" value="${['Admin']}" scope="page"/>

<%@include file="template_start.jsp" %>

<div class="container">
    <div class="row">
        <ul class="breadCrumbs list-inline">
            <li><a href="index.jsp">Главная</a></li>
            <span> > </span>
            <li><a href="admin_clients.jsp">Админка-Клиенты</a></li>
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
        <table id="clientsTable" class="table table-striped table-bordered" cellspacing='0'>
            <thead>
            <tr>
                <td>
                    <a href="#add_user_Modal" class="btn btn-primary" data-toggle="modal">Добавить</a>
                </td>
                <td>
                    <form action="clientsServlet.jsp">
                        <input type="submit" name="exportClients" class="exportButton btn btn-primary"
                               value="Экспорт клиентов"/>
                    </form>
                    <form action="clientsServlet.jsp">
                        <input type="submit" name="importClients" class="exportButton btn btn-primary"
                               value="Импорт клиентов"/>
                    </form>
                </td>
                <td>
                    <form action="clientsServlet.jsp">
                        <input type="submit" name="exportOrders" class="exportButton btn btn-primary"
                               value="Экспорт заказов"/>
                    </form>
                    <form action="clientsServlet.jsp">
                        <input type="submit" name="importOrders" class="exportButton btn btn-primary"
                               value="Импорт заказов"/>
                    </form>
                </td>
                <td>
                    <form action="cart.jsp">
                        <input type="submit" name="exportCarts" class="exportButton btn btn-primary"
                               value="Экспорт корзин"/>
                    </form>
                </td>
            </tr>
            <tr>
                <th>ID</th>
                <th>Имя</th>
                <th>Адрес</th>
                <th>Админ</th>
                <th colspan="2">Редактирование</th>
            </tr><!-- Table Header -->
            </thead>
            <tbody>
            <c:forEach items="${clientBean.getAll()}" var="item">
                <tr>
                    <td class="id"><c:out value="${item.id}"/></td>
                    <td class="name editable"><c:out value="${item.name}"/></td>
                    <td class="defaultAddress editable"><c:out value="${item.defaultAddress}"/></td>
                    <td class="isAdmin editable"><c:out value="${item.getAdmin()}"/></td>
                    <td>
                        <input type="submit" name="edit" class="btn btn-primary editClientButton" value="Изменить"/>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${clientBean.canRemove(item)}">
                                <a href="<c:url value="/clientsServlet.jsp?removeClient=&clientId=${item.id}"/>"
                                   class="btn btn-primary">Удалить</a>
                            </c:when>
                            <c:otherwise>
                                <a class="btn btn-primary">Неудаляемо</a>
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

<footer class="navbar-fixed-bottom">
    <div class="container">
        <div class="row">

        </div>
    </div>
</footer>

<!-- МОДАЛЬНЫЕ ОКНА -->
<!-- Добавить пользователя -->

<div class="modal fade" id="add_user_Modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2>Добавить пользователя</h2>
            </div>
            <div class="modal-body">
                <div class="row">
                    <form action="clientsServlet.jsp" class="addClientForm">
                        <table id="addClientsTable" class="table text-center" cellspacing='0'>
                            <tr class="row">
                                <td class="col">Имя</td>
                                <td class="col"><input name="clientName" type="text" value="${clientNameValue}"></td>
                            </tr>
                            <tr class="row">
                                <td class="col">Адрес</td>
                                <td class="col"><input name="clientDefaultAddress" type="text"
                                                       value="${clientDefaultAddressValue}"></td>
                            </tr>
                            <tr class="row">
                                <td class="col">Администратор</td>
                                <td class="col">
                                    <select name="clientIsAdmin">
                                        <option name="false"
                                                value="false"
                                                <c:if test="${false == clientIsAdminValue}">
                                                    selected
                                                </c:if>
                                        >
                                            false
                                        </option>
                                        <option name="true"
                                                value="true"
                                                <c:if test="${true == clientIsAdminValue}">
                                                    selected
                                                </c:if>
                                        >
                                            true
                                        </option>
                                    </select>
                                </td>
                            </tr>
                            <tr class="row">
                                <td>
                                    <input type="submit" name="addClient" class="saveAdd btn btn-primary"
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
