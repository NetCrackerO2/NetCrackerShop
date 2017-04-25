<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="env.jsp" %>

<c:set var="categories" value="${categoryBean.getAll()}"/>
<c:set var="title" value="Categories" scope="page"/>
<c:set var="pathStack" value="${['сategories']}" scope="page"/>
<c:set var="detailsPrefix">/products.jsp?category=</c:set>

<%@include file="template_start.jsp" %>
<aside>
    <div id="categoryName" class="text1"><c:out value="${title}"/></div>
    <div class="text3"><a href="search.jsp">Поиск</a></div>
</aside>
<div class="main">
        <c:forEach items="${categories}" var="item">
        <article class="z1">
            <a href="<c:url value="${detailsPrefix}${item.id}"/>">
                <h2><c:out value="${item.name}"/></h2>
            </a>
        </article>
        </c:forEach>
    <%@include file="template_end.jsp" %>
