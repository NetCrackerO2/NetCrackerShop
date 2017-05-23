<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="env.jsp" %>

<c:set var="categories" value="${categoryBean.getAll()}"/>
<c:set var="title" value="Категории" scope="page"/>
<c:set var="detailsPrefix">/products.jsp?category=</c:set>

<%@include file="template_start.jsp" %>
<c:set var="crumbs">
    <a href="index.jsp">Главная</a>,
    <a href="categories.jsp">Категории</a>
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
        <div class="col-md-8 col-md-offset-2">
            <table id="categories" class="categoryFont table table-striped table-bordered">
                <thead>
                <tr>
                    <th>Название</th>
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
</div>
</section>
