<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="env.jsp" %>

<%@include file="reqauth.jsp" %>

<c:choose>
    <c:when test='${param.category != null}'>
        <fmt:parseNumber var="categoryId" integerOnly="true" type="number" value="${param.category}"/>
        <c:set var="products" value="${productBean.getByCategory(categoryId)}"/>
        <c:set var="title" value="${categoryBean.get(categoryId).name}" scope="page"/>
    </c:when>
    <c:otherwise>
        <c:set var="products" value="${productBean.getAll()}"/>
        <c:set var="title" value="Products" scope="page"/>
    </c:otherwise>
</c:choose>
<c:set var="detailsPrefix">/product.jsp?id=</c:set>

<%@include file="template_start.jsp" %>
<nav>
    <div id="findBlock">
        <form action="" class="search">
            <table>
                <tr>
                    <td><input type="search" name="" placeholder="поиск" class="input"/></td>
                    <td><input type="submit" name="" value="" class="findButton"/></td>
                </tr>
            </table>
        </form>
    </div>
</nav>
<aside>
    <div id="categoryName" class="text1"><c:out value="${title}"/></div>
    <div class="text3">Что-то будет...</div>
</aside>
<div class="main">
    <c:forEach items="${products}" var="item">
    <article class="z1">
        <a href="<c:url value="${detailsPrefix}${item.id}"/>">
            <h2><c:out value="${item.name}"/></h2>
        </a>
        <desc><c:out value="${item.description}"/></desc>
        <price>$<c:out value="${item.price}"/></price>
    </article>
    </c:forEach>
    <%@include file="template_end.jsp" %>
