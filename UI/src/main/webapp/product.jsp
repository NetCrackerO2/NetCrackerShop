<%@include file="env.jsp"%>

<%@include file="reqauth.jsp"%>

<fmt:parseNumber var="productId" integerOnly="true" type="number" value="${param.id}" />
<c:set var="product" value="${productBean.get(productId)}" />
<c:set var="title" value="${categoryBean.get(productId).name}" scope="page"  />

<%@include file="template_start.jsp"%>
	<article class="z1">
		<h2><c:out value="${product.name}" /></h2>
		<desc><c:out value="${product.description}" /></desc>
		<price>$<c:out value="${product.price}" /></price>
		<form method="POST" action="#">
			<input type="number" id="count" min="1" max="100" /><button type=submit name=buy>&#x1F6D2;</button>
		</form>
	</article>
<%@include file="template_end.jsp"%>
