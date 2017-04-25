<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="env.jsp" %>

<%@include file="reqauth.jsp" %>

<c:set var="title" value="Админка-категории" scope="page"/>
<c:set var="objStack" value="${[title,'admin_category.jsp']}" scope="page"/>
<c:set var="pathStack" value="${[objStack]}" scope="page"/>
<%@include file="template_start.jsp" %>
<div class="row path">
    <ul class="list-inline">
        <li><a href="index.jsp">Главная</a></li>
        <span> > </span>
        <li><a href="admin_category.jsp">Админка-категории</a></li>
    </ul>
</div>
<div class="row">
    <div class="col-md-6 col-md-offset-2">
        <table id="categoryTable" class="table table-striped table-bordered" cellspacing='0'>
            <thead>
            <tr>
                <th>ID</th>
                <th>Наименование</th>
            </tr><!-- Table Header -->
            </thead>
            <tbody>
            <c:forEach items="${categoryBean.getAll()}" var="item">
                <tr>
                    <td class="editable"><c:out value="${item.id}"/></td>
                    <td class="editable"><c:out value="${item.name}"/></td>
                    <td>
                        <input type="submit" name="edit" class="btn btn-primary editButton" value="Изменить"/>
                    </td>
                    <td>
                        <a href="<c:url value="/categoriesServlet.jsp?removeCategory=&categoryId=${item.id}"/>"
                           class="btn btn-primary">Удалить</a>
                    </td>
                </tr>
            </c:forEach>
            <tr>
                <td>
                    <a href="#add_category_Modal" class="btn btn-primary" data-toggle="modal">Добавить</a>
                </td>
            </tr>
            </tbody>
        </table>
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