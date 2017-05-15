<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="env.jsp" %>

<div class="container">
    <div class="row">
        <c:forEach var="crumb" items="${param.crumbs}">
            <ul class="breadCrumbs list-inline">
                <li> > ${crumb}</li>
            </ul>
        </c:forEach>

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
    <div class="row">
        <ul class="list-inline navigate">
            <li><a href="categories.jsp">Категории</a></li>
            <li><a href="search.jsp">Поиск</a></li>
            <c:if test="${clientInfo.loggedIn}">
                <li><a href="user_profile.jsp">Личный кабинет</a></li>
                <li><a href="cart_view.jsp">Корзина</a></li>
                <c:if test="${clientInfo.getAdmin()}">
                    <li><a href="admin_view.jsp">Админ-панель</a></li>
                </c:if>
            </c:if>
        </ul>
    </div>
    <h2 class="text3"><c:out value="${param.title}"/></h2>
</div>
