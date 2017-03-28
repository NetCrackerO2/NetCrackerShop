<%@include file="env.jsp"%>

<%@include file="reqauth.jsp"%>

<c:choose>
	<c:when test='${param.category != null}'>
		<fmt:parseNumber var="categoryId" integerOnly="true" type="number" value="${param.category}" />
		<c:set var="products" value="${productBean.getByCategory(categoryId)}" />
		<c:set var="title" value="${categoryBean.get(categoryId).name}" scope="page"  />
	</c:when>
	<c:otherwise>
		<c:set var="products" value="${productBean.getAll()}" />
		<c:set var="title" value="Products" scope="page"  />
	</c:otherwise>
</c:choose>
<c:set var="detailsPrefix">/product.jsp?id=</c:set>

<%@include file="template_start.jsp"%>
	<c:forEach items="${products}" var="item">
		<article class="z1">
			<a href="<c:url value="${detailsPrefix}${item.id}"/>">
				<h2><c:out value="${item.name}" /></h2>
			</a>
			<desc><c:out value="${item.description}" /></desc>
			<price>$<c:out value="${item.price}" /></price>
		</article>
	</c:forEach>
<%@include file="template_end.jsp"%>
