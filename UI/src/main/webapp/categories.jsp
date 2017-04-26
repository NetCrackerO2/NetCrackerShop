<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="env.jsp" %>

<c:set var="categories" value="${categoryBean.getAll()}"/>
<c:set var="title" value="Категории" scope="page"/>
<c:set var="objStack" value="${['Категории','categories.jsp']}" scope="page"/>
<c:set var="pathStack" value="${[objStack]}" scope="page"/>
<c:set var="detailsPrefix">/products.jsp?category=</c:set>

<%@include file="template_start.jsp" %>

<div class="row path">
    <ul class="list-inline">
        <li><a href="index.jsp">Главная</a></li>
        <span> > </span>
        <li><a href="categories.jsp">Категории</a></li>
    </ul>
</div>
<!-- Главный Экран- -->
<div class="row">
    <div class="col-md-4 col-md-offset-3">
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
