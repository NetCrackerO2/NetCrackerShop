<%@include file="env.jsp"%>

<%@include file="reqauth.jsp"%>

<c:set var="categories" value="${categoryBean.getAll()}" />
<c:set var="title" value="Categories" scope="page"  />
<c:set var="detailsPrefix">/products.jsp?category=</c:set>

<%@include file="template_start.jsp"%>
	<c:forEach items="${categories}" var="item">
		<article class="z1">
			<a href="<c:url value="${detailsPrefix}${item.id}"/>">
				<h2><c:out value="${item.name}" /></h2>
			</a>
		</article>
	</c:forEach>
<%@include file="template_end.jsp"%>
