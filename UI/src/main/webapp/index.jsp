<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="env.jsp" %>
<%@include file="template_start.jsp" %>
<c:set var="title" value="Главная" scope="page"/>

<div class="container">
    <div class="row">
        <ul class="breadCrumbs list-inline">
            <li><a href="index.jsp">Главная</a></li>
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
<section id="content">
    <div class="container">
</div>
</section>

