<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="env.jsp" %>

<%@include file="reqauth.jsp" %>
<%@include file="reqadmin.jsp" %>

<c:set var="title" value="Админ-панель" scope="page"/>
<c:set var="pathStack" value="${['Admin']}" scope="page"/>

<%@include file="template_start.jsp" %>
<c:set var="crumbs">
    <a href="index.jsp">Главная</a>,
    <a href="admin_clients.jsp">Админ-панель: Клиенты</a>
</c:set>
<jsp:include page="menu.jsp">
    <jsp:param name="crumbs" value="${crumbs}"/>
</jsp:include>
</nav>

<!-- Главный Экран- -->
<div class="container">
    <div class="row">
        <hr>
        <div class="col-md-8 col-md-offset-1">
            <table id="clientsTable" class="table table-striped table-bordered" cellspacing='0'>
                <thead>
                <tr>
                    <td>
                        <a href="#add_user_Modal" class="btn btn-primary" data-toggle="modal">Добавить</a>
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
                <li class="list-group-item"><a href="admin_exp_imp.jsp">Экспорт/Импорт</a></li>
            </ul>
        </div>
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
