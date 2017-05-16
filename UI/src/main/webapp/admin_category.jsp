<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="env.jsp" %>

<%@include file="reqauth.jsp" %>
<%@include file="reqadmin.jsp" %>

<c:set var="title" value="Админ-панель: Категории" scope="page"/>
<c:set var="pathStack" value="${['Admin']}" scope="page"/>

<%@include file="template_start.jsp" %>
<c:set var="crumbs">
    <a href="index.jsp">Главная</a>,
    <a href="admin_category.jsp">Админ-панель: Категории</a>
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
        <div class="col-md-8 col-md-offset-1">
            <table id="categoryTable" class="table table-striped table-bordered" cellspacing='0'>
                <thead>
                <tr>
                    <td>
                        <a href="#add_category_Modal" class="btn btn-primary" data-toggle="modal">Добавить</a>
                    </td>
                </tr>
                <tr>
                    <th>ID</th>
                    <th>Название</th>
                    <th colspan="2">Редактирование</th>
                </tr><!-- Table Header -->
                </thead>
                <tbody>
                <c:forEach items="${categoryBean.getAll()}" var="item">
                    <tr>
                        <td class="id"><c:out value="${item.id}"/></td>
                        <td class="name contenteditable editable"><c:out value="${item.name}"/></td>
                        <td>
                            <input type="submit" name="edit" class="btn btn-primary editCategoryButton"
                                   value="Изменить"/>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${categoryBean.canRemove(item)}">
                                    <a href="<c:url value="/categoriesServlet.jsp?removeCategory=&categoryId=${item.id}"/>"
                                       class="btn btn-primary">Удалить</a>
                                </c:when>
                                <c:otherwise>
                                    <span class="reserve">Содержит заказанный товар</span>
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
                <li class="list-group-item"><a href="admin_exp_imp.jsp">Экспорт/Импорт</a></li>
            </ul>
        </div>
    </div>
</div>
</div>
</section>


<!-- МОДАЛЬНЫЕ ОКНА -->
<!-- Добавить категорию -->
<div class="modal fade" id="add_category_Modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2>Добавить категорию</h2>
            </div>
            <div class="modal-body">
                <div class="row">
                    <form action="categoriesServlet.jsp" class="addCategoryForm">
                        <table id="addCategoryTable" class="table text-center" cellspacing='0'>
                            <tr class="row">
                                <td class="col">Название</td>
                                <td class="col"><input name="categoryName" type="text" value="${categoryNameValue}">
                                </td>
                            </tr>
                            <tr class="row">
                                <td>
                                    <input type="submit" name="addCategory" class="saveAdd btn btn-primary"
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
