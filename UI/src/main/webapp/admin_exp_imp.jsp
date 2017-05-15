<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="env.jsp" %>

<%@include file="reqauth.jsp" %>
<%@include file="reqadmin.jsp" %>

<c:set var="title" value="Админ-панель" scope="page"/>
<c:set var="pathStack" value="${['Admin']}" scope="page"/>

<%@include file="template_start.jsp" %>
<c:set var="crumbs">
    <a href="index.jsp">Главная</a>,
    <a href="admin_clients.jsp">Админ-панель: Экспорт/Импорт</a>
</c:set>
<jsp:include page="menu.jsp">
    <jsp:param name="crumbs" value="${crumbs}"/>
</jsp:include>
</nav>

<!-- Главный Экран- -->
<div class="container">
    <div class="row">
        <div class="col-md-8 col-md-offset-1">
            <table class="table table-striped table-bordered">
                <thead>
                <tr>
                    <th></th>
                    <th>Экспорт</th>
                    <th>Импорт</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <th>Товары</th>
                    <td>
                        <form action="productsServlet.jsp">
                            <button type="submit" name="export" class="exportButton btn btn-primary"
                                    src="../image/cart.png">
                                <img class="icon" src="../image/export.png">
                            </button>
                        </form>
                    </td>
                    <td>
                        <form action="productsServlet.jsp">
                            <button type="submit" name="import" class="importButton btn btn-primary">
                                <img class="icon" src="../image/import.png">
                            </button>
                        </form>
                    </td>
                </tr>
                <tr>
                    <th>Категории</th>
                    <td>
                        <form action="categoriesServlet.jsp">
                            <button type="submit" name="exportCategories" class="exportButton btn btn-primary">
                                <img class="icon" src="../image/export.png">
                            </button>
                        </form>
                    </td>
                    <td>
                        <form action="categoriesServlet.jsp">
                            <button type="submit" name="importCategories" class="importButton btn btn-primary">
                                <img class="icon" src="../image/import.png">
                            </button>
                        </form>
                    </td>
                </tr>
                <tr>
                    <th>Клиенты</th>
                    <td>
                        <form action="clientsServlet.jsp">
                            <button type="submit" name="exportClients" class="exportButton btn btn-primary">
                                <img class="icon" src="../image/export.png">
                            </button>
                        </form>
                    </td>
                    <td>
                        <form action="clientsServlet.jsp">
                            <button type="submit" name="importClients" class="importButton btn btn-primary">
                                <img class="icon" src="../image/import.png">
                            </button>
                        </form>
                    </td>
                </tr>
                <tr>
                    <th>Заказы</th>
                    <td>
                        <form action="clientsServlet.jsp">
                            <button type="submit" name="exportOrders" class="exportButton btn btn-primary">
                                <img class="icon" src="../image/export.png">
                            </button>
                        </form>
                    </td>
                    <td>
                        <form action="clientsServlet.jsp">
                            <button type="submit" name="importOrders" class="importButton btn btn-primary">
                            <img class="icon" src="../image/import.png">
                            </button>
                        </form>
                    </td>
                </tr>
                <tr>
                    <th>Корзины</th>
                    <td>
                        <form action="cart.jsp">
                            <button type="submit" name="exportCarts" class="exportButton btn btn-primary">
                            <img class="icon" src="../image/export.png">
                            </button>
                        </form>
                    </td>
                    <td>
                        <form action="cart.jsp">
                            <button type="submit" name="importCarts" class="importButton btn btn-primary">
                                <img class="icon" src="../image/import.png">
                            </button>
                        </form>
                    </td>
                </tr>
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
</section>

<footer class="navbar-fixed-bottom">
    <div class="container">
        <div class="row">

        </div>
    </div>
</footer>

<script src="../js/func.js"></script>
<%@include file="template_end.jsp" %>
