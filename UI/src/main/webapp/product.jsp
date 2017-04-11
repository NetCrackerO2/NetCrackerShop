<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="env.jsp" %>

<fmt:parseNumber var="productId" integerOnly="true" type="number" value="${param.id}"/>
<c:set var="product" value="${productBean.get(productId)}"/>
<c:set var="title" value="${categoryBean.get(productId).name}" scope="page"/>

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
    <article class="z1">
        <h2><c:out value="${product.name}"/></h2>
        <desc><c:out value="${product.description}"/></desc>
        <price>$<c:out value="${product.price}"/></price>
        <form method="POST" action="/cart.jsp">
            <input type="hidden" name="id" value="<c:out value="${product.id}" />"/>
            <input type="number" name="count" min="1" max="100" value="1"/>
            <button type=submit name=buy>&#x1F6D2;</button>
        </form>
    </article>
    <%@include file="template_end.jsp" %>
