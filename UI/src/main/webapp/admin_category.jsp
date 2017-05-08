<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="env.jsp" %>

<%@include file="reqauth.jsp" %>

<c:set var="title" value="Admin panel" scope="page"/>
<c:set var="pathStack" value="${['Admin']}" scope="page"/>

<%@include file="template_start.jsp" %>
<!-- Главный Экран- -->
<div class="container">
    <div class="row">
        <ul class="breadCrumbs list-inline">
            <li><a href="index.jsp">Главная</a></li>
            <span> > </span>
            <li><a href="admin_category.jsp">Админка-категории</a></li>
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
<div class="row">
    <div class="col-md-6 col-md-offset-2">
        <table id="categoryTable" class="table table-striped table-bordered" cellspacing='0'>
            <thead>
            <tr>
                <td>
                    <a href="#add_category_Modal" class="btn btn-primary" data-toggle="modal">Добавить</a>
                </td>
                <td>
                    <form action="categoriesServlet.jsp">
                        <input type="submit" name="exportCategories" class="exportButton btn btn-primary"
                               value="Экспорт"/>
                    </form>
                </td>
            </tr>
            <tr>
                <th>ID</th>
                <th>Наименование</th>
                <th colspan="2">Редактирование</th>
            </tr><!-- Table Header -->
            </thead>
            <tbody>
            <c:forEach items="${categoryBean.getAll()}" var="item">
                <tr>
                    <td class="id"><c:out value="${item.id}"/></td>
                    <td class="name editable"><c:out value="${item.name}"/></td>
                    <td>
                        <input type="submit" name="edit" class="btn btn-primary editCategoryButton" value="Изменить"/>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${categoryBean.canRemove(item)}">
                                <a href="<c:url value="/categoriesServlet.jsp?removeCategory=&categoryId=${item.id}"/>"
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
                                <td class="col"><input name="categoryName" type="text"></td>
                            </tr>
                            <tr class="row">
                                <td class="col">parentID</td>
                                <td class="col"><input name="parentCategoryId" type="text"></td>
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
