<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="env.jsp" %>

<%@include file="reqauth.jsp" %>

<c:set var="title" value="Admin panel" scope="page"/>
<c:set var="pathStack" value="${['Admin']}" scope="page"/>

<%@include file="template_start.jsp" %>
<aside class="col-md-2">
    <ul class="list-group submenu">
        <li class="list-group-item"><a href="admin_view.jsp">Работа с товаром</a></li>
        <li class="list-group-item"><a href="admin_category.jsp">Работа с категориями</a></li>
        <li class="list-group-item"><a href="admin_clients.jsp">Работа с клиентами</a></li>
    </ul>
</aside>
<!-- Главный Экран- -->
<section id="content">
    <div class="container">
        <div class="row">
            <div class="col-md-6 col-md-offset-2">

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
                    </tr><!-- Table Header -->
                    </thead>
                    <tbody>
                    <c:forEach items="${clientBean.getAll()}" var="item">
                        <tr>
                            <td class="editable"><c:out value="${item.id}"/></td>
                            <td class="editable"><c:out value="${item.name}"/></td>
                            <td class="editable"><c:out value="${item.defaultAddress}"/></td>
                            <td>
                                <input type="submit" name="edit" class="btn btn-primary editButton" value="Изменить"/>
                            </td>
                            <td>
                                <a href="<c:url value="/clientsServlet.jsp?removeClient=&clientId=${item.id}"/>"
                                   class="btn btn-primary">Удалить</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
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
                                <td class="col"><input name="clientName" type="text"></td>
                            </tr>
                            <tr class="row">
                                <td class="col">Адрес</td>
                                <td class="col"><input name="clientDefaultAddress" type="text"></td>
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