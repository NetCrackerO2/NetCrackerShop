<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="env.jsp" %>

<c:set var="categories" value="${categoryBean.getAll()}"/>
<c:set var="title" value="Категории" scope="page"/>
<c:set var="objStack" value="${['Категории','categories.jsp']}" scope="page"/>
<c:set var="pathStack" value="${[objStack]}" scope="page"/>
<c:set var="detailsPrefix">/products.jsp?category=</c:set>

<%@include file="template_start.jsp" %>
<div class="container">
    <div class="row">
        <ul class="breadCrumbs list-inline">
            <li><a href="index.jsp">Главная</a></li>
            <span> > </span>
            <li><a href="categories.jsp">Категории</a></li>
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
    <div class="col-md-4 col-md-offset-4">
        <table class="table table-striped table-bordered">
            <thead>
            <tr>
                <th>Наименование</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${categories}" var="item">
                <tr>
                    <td>
                        <a href="<c:url value="${detailsPrefix}${item.id}"/>">
                            <c:out value="${item.name}"/>
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</div>
</section>
