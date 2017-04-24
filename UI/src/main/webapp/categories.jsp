<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="env.jsp" %>

<c:set var="categories" value="${categoryBean.getAll()}"/>
<c:set var="title" value="Categories" scope="page"/>
<c:set var="pathStack" value="${['Categories']}" scope="page"/>
<c:set var="detailsPrefix">/products.jsp?category=</c:set>

<%@include file="template_start.jsp" %>

<!-- Главный Экран- -->
<section id="content">
    <div class="container">
        <div class="row">
            <div class="col-md-6 col-md-offset-3">
                <c:forEach items="${categories}" var="item">
                    <a href="<c:url value="${detailsPrefix}${item.id}"/>">
                        <h2><c:out value="${item.name}"/></h2>
                    </a>
                </c:forEach>
            </div>
        </div>
    </div>
</section>
